# AntPlus2NIXConverter
The library for storing data from ANT+ profiles (https://www.thisisant.com/developer/ant-plus/ant-plus-basics) into the NIX data format  (https://github.com/G-Node/nix)


# Build AntPlus2NIXConverter library

###Build project and generate jars :
You must download pom.xml and get it to root folder of project
```
> mvn package shade:shade -DnixIncludePath=<path>

```
nixIncludePath should contain jar file with nix.  
In tagret directory you can find jar file, which you can add to your project.

# NIX library on Windows

Software requirements:
  - Visual Studio 2013 express for windows desktop installed
  - Java 1.8
  - Maven 3.2.5
  - Platform x86

## Donwload dependencies

Download [nix-dependencies-windows-20150206.zip](https://projects.g-node.org/nix/) and unzip it to C:\deps.

Run C:\deps\nixenv.bat

## Build NIX on Windows
```
> git clone https://github.com/G-Node/nix.git
> cd nix
> mkdir build
> cd build
> cmake .. -G"Visual Studio 12"
> cmake --build . --config Release
```

## Build nix-java on Windows
```
> git clone https://github.com/G-Node/nix-java.git
> cd nix-java
> mvn clean package -DnixIncludePath=path/to/nix/include -DboostIncludePath=%BOOST_INCLUDEDIR% -DnixLinkPath=path/to/nix/build/Release -Dhdf5LinkPath=%HDF5_BASE%/bin -DskipTests
```

In nix-java/tagret directory you can find jar file, which you can add to your project.


# NIX on Linux (Ubuntu)

You have to add & install the following software source to your system:

Trusty:
```
> deb http://ppa.launchpad.net/gnode/nix/ubuntu trusty main
> deb-src http://ppa.launchpad.net/gnode/nix/ubuntu trusty main
```
Xenial:
```
> deb http://ppa.launchpad.net/gnode/nix/ubuntu xenial main
> deb-src http://ppa.launchpad.net/gnode/nix/ubuntu xenial main
```

by executing:
```
> sudo add-apt-repository ppa:gnode/nix
> sudo apt-get update
> sudo apt-get install libnix-dev
```

## Build NIX under Ubuntu

##### Dependencies

In order to build the NIX library a recent C++11 compatible compiler is needed (g++ >= 4.8, clang >= 3.4) as well as the build tool CMake (>= 2.8.9). Further nix depends on the following third party libraries:

- HDF5 (version 1.8.13 or higher)
- Boost (version 1.49 or higher)
- CppUnit (version 1.12.1 or higher)

##### Instructions

```
# 1 install dependencies
sudo apt-get install libboost-all-dev libhdf5-serial-dev libcppunit-dev cmake build-essential

**Note:** If the standard version of the boost libraries in your distribution is less than 1.49,
# manually install a version larger than 1.49 from the launchad (https://launchpad.net/~boost-latest/+archive/ubuntu/ppa)

# 2 clone NIX
git clone https://github.com/G-Node/nix
cd nix

# 3 make a build dir and build nix
mkdir build
cd build
cmake ..
make all

# 4 run the unit tests
ctest

# 5 install
sudo make install
```

## Build nix-java on Linux

##### Prerequisities

- Java 1.8
- Maven 3

##### Instructions

```
> git clone https://github.com/G-Node/nix-java
> cd nix-java
```

Build clean project and generate jars:
```
> mvn clean package
```

To specify include a link path:
```
> mvn clean package -DnixIncludePath=<path> -DnixLinkPath=<path>
```

Sample usage:
```
> mvn clean package -DnixIncludePath=/usr/include/nix -DnixLinkPath=/usr/local/lib //optional -DskipTests
```
In nix-java/tagret directory you can find jar file, which you can add to your project.

### After build

To make everything running, you have to add to your project javacpp library too. You can download it from [here](http://bytedeco.org/download/) for example.

Now you can add AntPlus2NIXConverter to your project and use it without any restrictions.
