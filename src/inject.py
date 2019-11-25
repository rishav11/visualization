# Inject logger lines into java src code

from os import walk
from os import path
import re

for (root, dirs, files) in walk("../source_code/src/"):
    for filename in files:
        if filename.endswith("QUANTIFIER.java"):
            javaFile = path.join(root, filename)
            f = open(javaFile, "r")
            data = f.readlines()
            f.close()

            isLoggerLineAdded = False
            inCommentBlock = False
            inGlobalVariableSection = False
            globalVariables = []

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
                elif line.find("@") >= 0 or line.find("{") >= 0 or line.find("(") >= 0:
                    inGlobalVariableSection = None
                elif inGlobalVariableSection:
                    gv = re.match(".*\s+(\w+)\s*[;=].*", line)
                    if gv:
                        globalVariables.append(gv.group(1))

                for gv in globalVariables:
                    if re.search("^\s*(" + gv + ")+\s*=.*$", line):
                        data.insert(i+1, "Logger.logTwo(this, \"" + gv + "\");\n")

            f = open(javaFile, "w")
            for line in data:
                f.write(line)
            f.close()
