// Global variable to keep track of chaning classes.
var starColor = getRandomColor();
/* Not needed anymore but keeping it just in case.

         // Gets & Prints the number of variables that there are.
         document.getElementById('demo2').innerHTML = data["ast.EXPRESSION"]["@2cb4c3ab"] ;

         //Creates an array of the number of variables there are in the js object and prints it on screen.
         const numberOfVariables = Object.values(data["ast.EXPRESSION"]["@2cb4c3ab"]) ;
         document.getElementById('demo2').innerHTML = numberOfVariables.length ;

        */
// Code for the drawing part of this project.
Object.keys(data).forEach(className => {
    // console.log(className + ": " + JSON.stringify(data[className]))
    h3 = document.createElement("h3");
    h3.innerHTML = className;
    div = document.createElement("div");
    div.id = className;
    document.body.appendChild(h3);
    document.body.append(div);
    Object.keys(data[className]).forEach(instance =>{
        for(var i = 0; i < Object.keys(data[className][instance]).length; i++) {
            // Gets & prints the data from the sample.js file.
            // document.getElementById('demo1').innerHTML = data["ast.EXPRESSION"]["@2cb4c3ab"][i+1] ;

            // Creates an array of the values inside the js object.
            const numberOfValues = Object.values(data[className][instance]) ;

            //Prints those values to the console.
            //console.log(numberOfValues) ;

            // Prints the length of the array that is made up of the changes that a variabe
            // goes through in another program.
            //document.getElementById('demo1').innerHTML = numberOfValues[i].length ;

            var elem = document.getElementById(className);
            tooltip = document.createElement("div");
            tooltipText = document.createElement("span");
            tooltip.setAttribute("class", "tooltip" );
            tooltipText.setAttribute("class", "tooltiptext" );
            var variableValue = Object.values(data[className][instance])[i];
            tooltipText.innerHTML = variableValue;
            elem.append(tooltip);
            tooltip.appendChild(tooltipText);

            var two = new Two({ width: 200, height: 200 }).appendTo(tooltip);
            var star = two.makeStar(0, 0, 55, 15, numberOfValues[i].length );
            star.fill = starColor;
            star.stroke = hexToComplimentary(starColor) ;
            var variableName = new Two.Text(Object.keys(data[className][instance])[i], 0, 70, starColor);
            var changedTimes = numberOfValues[i].length;
            var changeText = "Value changed " +  numberOfValues[i].length + (numberOfValues[i].length > 1 ? " times" : " time");
            var variableValueChange = new Two.Text(changeText, 0, 90, starColor);
            // Groups can take an array of shapes and/or groups.
            var group = two.makeGroup(star, variableName, variableValueChange);
            // And have translation, rotation, scale like all shapes.
            group.translation.set(two.width / 2, two.height / 2);
            //  group.rotation = Math.PI;

            // You can also set the same properties a shape have.
            group.linewidth = 4;
            if (changedTimes > 1) {
                star.sides = 0;
                two.bind('update', function (frameCount) {
                    // This code is called everytime two.update() is called.
                    // Effectively 60 times per second.

                    if (star.scale > 0.9999) {
                        star.scale = star.rotation = 0;
                    }
                    var t = (1 - star.scale) * 0.09 * Math.PI;
                    if (star.sides >= changedTimes) {
                        star.sides = 0;
                    }
                    if (star.sides + t > changedTimes) {
                        star.sides = changedTimes;
                        t = 0;
                    }
                    star.scale += t;
                    star.sides += t;

                }).play();
            } else {
                two.update();
            }
        }
    });
    starColor = getRandomColor();
});