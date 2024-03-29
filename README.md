<p align="center">
  <img src="/src/logo.png" width="150px" height="150px" alt="Vistar Logo" style="text-align: center"/>
  <h3 align="center">Vistar</h3>
  <p align="center">A visualization tool for Java programs that visualizes the number of global variable changes as stars.</p>
</p>

<!-- ABOUT THE PROJECT -->
## About The Project
For our project, we were really motivated to help solve a problem that we've all been dealing with and that is that we hated adding debug messages to our Java programs. So for our visualization project we decided to solve this problem by representing how many times a global variables changes (and the corresponding values) as a X-pointed star (where X is the number of times a the varaibles has changed).

### Software Engineering Task
Our main goal of this was to help software developers with debugging so that they can spend less time writing debug messages and quickly be able to verify if global variables were being updated as expected.

Our visualization will help identify:
* classes, class instances, declared fields and field values
* how many times a variable has changed
* which classes make the most variable changes
* any unnecessary or redundant variables

## Analysis Used
Our visualization uses **semantic dynamic analysis** to achieve the desired result.
* Speifically, in Java we used the built-in reflection class to get class names, class instances, declared fields and field values.
* We then have a method called `log()` that is injected (via a python script) into all Java files at lines where a global variable is changed. 

<!-- GETTING STARTED -->
## Getting Started

To analyze your own Java program, follow these instructions.

### Prerequisites

Make sure you have python installed.

### Making the Visualization

1. Open `analysis/inject.py` and change `../source_code/src/` on `line 9` to point to the source folder of your Java program. This currently points to a sample Java program located in the repo.
2. Run:
```bash
cd analysis
python inject.py
```
3. Run your Java program as you normally would. The logger lines that were injected in step 2 will print all neccessary data to `variables_log.txt`.
4. Once the Java program has stopped running, open `json_converter/converter_beta.py` and change `../source_code/variables_log.txt` on `line 8` to point to the `variables_log.txt` file in your Java program.
5. Run:
```bash
cd json_converter
python converter_beta.py
```
6. Open `src/Interface.HTML` in your web browser to see the visualization.

<!-- CONTRIBUTORS -->
## Contributors

### Viniel
* Created Logger class which gets class names, class instances, global variables and their values and writes it to variables_log.txt
* Wrote python script to copy logger class to source directory and inject logger lines into Java files at lines where a global variable is changed
* Helped perform user studies

### Ze
* Manually added logger lines to sample source code and tested Logger class
* Wrote python script to parse variables_log.txt and turn it into a json object
* Helped perform user studies
* Put together video

### Rishav
* Loaded json object into javascript and looped through object to draw stars for each global variable
* Made stars different colours based on their class
* Styling of stars and visualization layout
* Helped perform user studies

### Ann
* Looped through json object to group stars by class
* Created tooltip to display values of variables on hover
* Animated stars and styled groups and text to make it easy to read and understand
* Helped perform user studies

<!-- USER TESTING -->
## User Testing
Questions asked:
1. Which variable changed the most number of times?
2. Which class did the most changed variable belong to?
3. What values did the most changed variable have?

### Prototype Testing
Our original design.

![Vis Prototype](/images/vis_prototype.png)

Results of prototype testing.
* User 1 was able to answer questions 1 and 2 (question 3 was not asked), however they did not think the visualization was helpful because it only showed the name of the variable and the number of times it was being changed. Suggestion was to somehow include the values of the variable.

### End-User Testing
#### Test 1
Intermediate design.

![Animation Loop](/images/animation_loop.gif)

Results of end-user test 1
* User 2 was able to answer all 3 questions correctly, however the they thought that the looping animation of the stars was distracting and did not add any additional value.

#### Test 2
Final design.

![Final Design](/images/vis_screenshot.png)

Results of end-user test 2
* User 3 was able to answer all 3 questions correctly. We kept the animation, but we got rid of the looping and stopped the animation after it was done playing once.
