# Animator

An academic project from Northeastern's Object Oriented Design course. 
It's a program used to create a simple animation from a text file of descriptions of shapes.
This project uses the Model View Controller architecture and uses instances of design patterns such as
the builder design pattern and the factory pattern.


## Getting Started


### Installing

* cd into the directory that you want this folder to be in and clone the repository using
```git clone https://github.com/dorothysyu/animator.git``` or download the zip file. 
* cd into the `animator/jar` directory to be able to execute the commands.


### Usage

``` 
java -jar  Animator.jar -if [filename] -iv [view option] [optional commands]
```
List of commmand line options:
```
-if                     Name of the file of shape descriptions. 
                        Examples of such files are included in the jar folder, ending with *.txt. Ex:
                        big-bang-big-crunch.txt
                        buildings.txt
                        smalldemo.txt
                        toh-3.txt
                        toh-8.txt
                        toh-12.txt
                        
-iv                     Specify the view you want the animation to be in:
                        visual
                        text
                        svg
                        
-speed                  Specify how fast you want the animation to be (measured in ticks per second)

-out                    Specify what the output will be written to.
```
### Examples
Use `smalldemo.txt` for the animation file, open a text view with its output going to System.out, and a speed of 0.5 ticks per second:
```
java -jar Animator.jar -if smalldemo.txt -iv text -o out -speed 0.5
```

Use `buildings.txt`, open an SVG view with the output going to the file buildings.svg with a speed of 50 ticks per second:
```
java -jar Animator.jar -if buildings.txt -iv svg -o builings.svg -speed 50
```

Use `big-bang-big-crunch.txt` and open a visual view to show the animation at 100 ticks per second.
```
java -jar Animator.jar -if big-bang-big-crunch.txt -iv visual -o out -speed 100
```

## Authors

Dorothy Yu and [Jocelyn Chan](https://www.linkedin.com/in/jocelyn-chan-sw/)

## Acknowledgements
Vidoje Mihajlovikj - Instructor for the OOD class
OOD TAs - helpfully guided me and my partner to various solutions