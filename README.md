# AntPlus2NIXConverter
The library for storing data from ANT+ profiles (https://www.thisisant.com/developer/ant-plus/ant-plus-basics) into the NIX data format  (https://github.com/G-Node/nix)

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
>git clone https://github.com/G-Node/nix.git
>cd nix
>mkdir build
>cd build
>cmake .. -G"Visual Studio 12"
>cmake --build . --config Release
```

## Build nix-java on Windows
```
>git clone https://github.com/G-Node/nix-java.git
>cd nix-java
>mvn clean package -DnixIncludePath=path/to/nix/include -DboostIncludePath=%BOOST_INCLUDEDIR% -DnixLinkPath=path/to/nix/build/Release -Dhdf5LinkPath=%HDF5_BASE%/bin -DskipTests
```

In nix-java/tagret directory you can find jar file, which you can add to your project.

To make everything running, you have to add to your project javacpp library too. You can download it from [here](http://bytedeco.org/download/) for example.

Now you can add AntPlus2NIXConverter to your project and use it without any restrictions.
