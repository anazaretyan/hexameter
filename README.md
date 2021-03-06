# Hexameter

Hexameter is a hexagonal grid library. The motivation behind it is to have
an optimized, simple and usable library for drawing hexagonal grids without
being tied to any GUI framework.
It is **100% unit tested** *(apart from some generated code)*.

This means that you can use Hexameter on Android, your backend or your desktop app.
There is a REST-based web example which you can tinker with [here][herokurestlink].
You can also check out more code examples in the hexameter-examples project [here][exampleprojectslink].

Hexameter currently supports a maximum grid size of 1000 * 1000 (1.000.000 cells).
This is a known limitation and it will be worked around in a later release.

Now Hexameter **has been backported** to support Android!

Note that this library uses [RxJava][rxlink]. You should familiarize yourself with the basics (nothing more needed) in order
to use it effectively. If you don't want to learn [RxJava][rxlink] don't worry the code examples below can be used without
diving into the details.

[![][travis img]][travis]
[![][maven img]][maven]
[![][codecov img]][codecov]
[![][license img]][license]

# Getting started

This library uses [Amit's guide to hexagonal grids][amitlink]. The coordinate system used by this library is the Axial coordinate system.
Please check [here][axialcoords] for further details.

Hexagonal grids come in **flat topped** and **pointy topped** shapes. The grid can have several layouts:
 - Hexagonal: the width and height of a this layout has to be equal and both have to be an odd number.
 - Triangular: the width and height of a this layout has to be equal.
 - Rectangular: no special rules
 - Trapezoid: no special rules

All layouts have *with* and *height* values of at least **1**.
You can consult [HexagonalGridLayout][hexgridlayout] if you need further details.

This library is not tied to any GUI implementation. All operations provided by the [API][api] are working using the most abstract concept possible.

## Basic usage

### Maven dependency
Let's start by adding Hexameter as a Maven dependency to your project:

    <dependency>
	    <groupId>org.codetome</groupId>
	    <artifactId>hexameter</artifactId>
	    <version>2.0.0</version>
    </dependency>
    
You can also use Gradle:

    'org.codetome:hexameter:2.0.0'


### Creating a grid

You can use the [HexagonalGridBuilder][hexgridbuilder] from the API package to create a [HexagonalGrid][hexgrid]:

    import org.codetome.hexameter.core.api.HexagonalGridLayout;
    import org.codetome.hexameter.core.api.HexagonOrientation;
    import org.codetome.hexameter.core.api.HexagonalGrid;
    import org.codetome.hexameter.core.api.HexagonalGridBuilder;
    
    import static org.codetome.hexameter.core.api.HexagonalGridLayout.RECTANGULAR;
    import static org.codetome.hexameter.core.api.HexagonOrientation.FLAT_TOP;
    // ...
    private static final int GRID_HEIGHT = 9;
    private static final int GRID_WIDTH = 9;
    private static final HexagonalGridLayout GRID_LAYOUT = RECTANGULAR;
    private static final HexagonOrientation ORIENTATION = FLAT_TOP;
    private static final double RADIUS = 30;

    // ...
    HexagonalGriBuilder builder = new HexagonalGridBuilder()
        .setGridHeight(GRID_HEIGHT)
        .setGridWidth(GRID_WIDTH)
        .setGridLayout(GRID_LAYOUT)
        .setOrientation(ORIENTATION)
        .setRadius(RADIUS);

    HexagonalGrid grid = builder.build();

You can also use the [HexagonalGridBuilder][hexgridbuilder] to create a [HexagonalGridCalculator][hexgridcalc] for you which supports advanced operations
on [HexagonalGrid][hexgrid]s:

    import org.codetome.hexameter.core.api.HexagonalGridCalculator;
    // ...
    HexagonalGridCalculator calculator = builder.buildCalculatorFor(grid);

### Drawing a grid

First you want to fetch all the `Hexagon`s from your grid:

    Collection<Hexagon> hexagons = grid.getHexagons();

After that you can iterate over all the `Point`s of your `Hexagon`s:

    hexagonalGrid.getHexagons().forEach(new Action1<Hexagon>() {
        @Override
        public void call(Hexagon hexagon) {
            for(Point point : hexagon.getPoints()) {
                // your draw logic here
            }
        }
    });
	

Note that each `Point` represents a coordinate in 2D space. You can use them for drawing.

### Manipulating your grid

There are basically only one operation for manipulating your data on the grid:
The `Hexagon#setSatelliteData(T data)` operation with which you can add your own arbitrary
data to a `Hexagon` object. This means that once created a `HexagonalGrid` is immutable apart from the
satellite data you add.

There is also a `HexagonalGrid#clearSatelliteData()` method for clearing all satellite data from your grid.

The implementation of the `HexagonalGrid` is lazy. This means that it only stores data which is absolutely necessary
to keep in memory (the coordinates and your satellite data). Everything else is generated on the fly. The only limiting
factor of a grid at the moment is the coordinates (which consume memory) and the satellite data. This will be worked
around later.

### GUI example:

You can find a simple GUI example in the `hexameter-swt-example` submodule. Run it by doing the following steps.

1. Clone the project: `git clone git@github.com:adam-arold/hexameter.git`
2. cd to the newly created `hexameter` folder: `cd hexameter/`
3. build the project: `mvn clean install`
4. run the created uberjar: `java -jar ./hexameter-swt-example/target/hexameter-swt-example-1.0.0.jar`


### Supported operations
 - Getting a hexagon by its grid coordinate
 - Getting a hexagon by its pixel coordinate
 - Getting the neighbors of a hexagon
 - Calculating the distance between two hexagons
 - Calculating the movement range from a hexagon
 - Checking whether a Hexagon is on a grid or not
 - Adding custom data to a Hexagon
 - Clearing all custom data from the HexagonalGrid
 - Getting a subset of Hexagons (using axial or offset coordinate range) from the grid

Check these interfaces for more details:

- [HexagonalGridBuilder][hexgridbuilder]
- [HexagonalGrid][hexgrid]
- [HexagonalGridCalculator][hexgridcalc]
- [Hexagon][hex]

## Road map:
 - Field of view calculation with obstacles (blocking vision)
 - Path finding with obstacles  (blocking movement)
 - Movement range with obstacles and movement cost calculation
 - Rotation calculation
 - Option for arbitrary storage objects thus alleviating the 1000*1000 limit
 - Android example

## License
Hexameter is made available under the [MIT License](http://www.opensource.org/licenses/mit-license.php).

## Credits
Hexameter is created and maintained by Adam Arold

*I'm open to suggestions, feel free to comment or to send me a message.
Pull requests are also welcome!*

[travis]:https://travis-ci.org/Hexworks/hexameter
[travis img]:https://api.travis-ci.org/Hexworks/hexameter.svg?branch=master

[maven]:http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.codetome%22%20AND%20a%3A%22hexameter-core%22
[maven img]:https://maven-badges.herokuapp.com/maven-central/org.codetome/hexameter-core/badge.svg

[codecov img]:https://codecov.io/github/Hexworks/hexameter/coverage.svg?branch=master
[codecov]:https://codecov.io/github/Hexworks/hexameter?branch=master

[license]:https://github.com/Hexworks/hexameter/blob/master/LICENSE
[license img]:https://img.shields.io/badge/License-MIT-blue.svg

[amitlink]:http://www.redblobgames.com/grids/hexagons/
[axialcoords]:http://www.redblobgames.com/grids/hexagons/#coordinates
[herokurestlink]:http://hexameter-rest-example.herokuapp.com/
[exampleprojectslink]:https://github.com/Hexworks/hexameter/tree/master/hexameter-examples

[hexgridlayout]:https://github.com/Hexworks/hexameter/blob/master/hexameter-core/src/main/java/org/codetome/hexameter/core/api/HexagonalGridLayout.java
[hexgridbuilder]:https://github.com/Hexworks/hexameter/blob/master/hexameter-core/src/main/java/org/codetome/hexameter/core/api/HexagonalGridBuilder.java
[api]:https://github.com/Hexworks/hexameter/tree/master/hexameter-core/src/main/java/org/codetome/hexameter/core/api
[hexgrid]:https://github.com/Hexworks/hexameter/blob/master/hexameter-core/src/main/java/org/codetome/hexameter/core/api/HexagonalGrid.java
[hexgridcalc]:https://github.com/Hexworks/hexameter/blob/master/hexameter-core/src/main/java/org/codetome/hexameter/core/api/HexagonalGridCalculator.java
[hex]:https://github.com/Hexworks/hexameter/blob/master/hexameter-core/src/main/java/org/codetome/hexameter/core/api/Hexagon.java
[rxlink]:https://github.com/ReactiveX/RxJava/wiki/How-To-Use-RxJava
