# Inject logger lines into java src code

from os import walk
from os import path
from os import mkdir
from shutil import copyfile
import re

src_directory = "../source_code/src/"

for (root, dirs, files) in walk(src_directory):
    for filename in files:
        if filename.endswith(".java"):
            javaFile = path.join(root, filename)
            f = open(javaFile, "r")
            data = f.readlines()
            f.close()

            isLoggerLineAdded = False
            inCommentBlock = False
            inGlobalVariableSection = False
            globalVariables = []
            inStaticMethod = False
            staticMethodStack = []

            for i, line in enumerate(data):
                if isLoggerLineAdded is False:
                    if line.find("/*") >= 0:
                        inCommentBlock = True
                    elif line.find("*/") >= 0:
                        inCommentBlock = False
                    elif line.find("package")==-1 and not inCommentBlock and line.find("//")==-1:
                        data.insert(i, "import logger.Logger;\n")
                        isLoggerLineAdded = True
                elif line.find("class") >= 0:
                    if not inGlobalVariableSection is None:
                        inGlobalVariableSection = True
                elif line.find("@") >= 0 or line.find("{") >= 0:
                    inGlobalVariableSection = None
                elif inGlobalVariableSection:
                    gv = re.match(".*\s+(\w+)\s*[;=].*", line)
                    if gv:
                        globalVariables.append(gv.group(1))

                if inStaticMethod is False:
                    if line.find("static") >= 0:
                        inStaticMethod = True
                    if line.find("{") >= 0:
                        staticMethodStack.append("{")
                else:
                    if line.find("{") >= 0:
                        staticMethodStack.append("{")
                    if line.find("}") >= 0:
                        staticMethodStack.pop()
                    if len(staticMethodStack) == 0:
                        inStaticMethod = False

                for gv in globalVariables:
                    if re.search("^\s*(" + gv + ")\s*(=|\.add|\.put|\+\+|\-\-|\.append).*$", line) and not inStaticMethod:
                        data.insert(i+1, "Logger.log(this, \"" + gv + "\");\n")

            f = open(javaFile, "w")
            for line in data:
                f.write(line)
            f.close()

try:
    mkdir(src_directory + "logger")
    copyfile('./logger/Logger.java', src_directory + "logger/Logger.java")
except OSError:
    print ("Error, try again")
else:
    print ("Success")