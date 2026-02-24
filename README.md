# CTNH-Core

[![Build](https://github.com/CTNH-Team/CTNH-Core/actions/workflows/build.yml/badge.svg?branch=dev)](https://github.com/CTNH-Team/CTNH-Core/actions/workflows/build.yml)

Core mod for the modpack Create: New Horizon (CTNH).

## Building

This mod should be built under [CTNH-Team/CTNH-Modules](https://github.com/CTNH-Team/CTNH-Modules) repository using Gradle.

```shell
$ git clone --recursive https://github.com/CTNH-Team/CTNH-Modules.git 
$ cd CTNH-Modules
$ ./gradlew :modules:CTNH-Core:build            # To build the mod .jar
$ ./gradlew :modules:CTNH-Core:runData          # To generate data
$ ./gradlew :modules:CTNH-Core:spotlessCheck    # To check code formatting
$ ...
```

Nightly builds are available on the [Actions](https://github.com/CTNH-Team/CTNH-Core/actions/workflows/build.yml) page.

## Credits

- [GregTech-Addon-Template](https://github.com/JuiceyBeans/GregTech-Addon-Template) 
- Some machines from [GTNN](https://github.com/Arborsm/GT--) and [GTMThings](https://github.com/liansishen/GTMThings)

## License

All code is licensed under the [GNU LGPL v3 License](https://www.gnu.org/licenses/lgpl-3.0.en.html).

All artwork (images, textures, models, animations, etc.) is licensed under the [Creative Commons Attribution-NonCommercial 4.0 International License](http://creativecommons.org/licenses/by-nc/4.0/), unless stated otherwise.
