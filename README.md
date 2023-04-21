# !!! THIS PROJECT IS PRE-RELEASE !!!

# m2gcc
> M2GCC is a Build Plugin for Maven utilizing GCC

**m2gcc** provides a mundle as well as its component modules for use as maven build plugins.
The plugin is intended to be used with `compiler-maven-extension` in conjunction with `r4m-maven-extension`.

## Usuage

### Manual

In case you wish to run these goals manually you need to adhere to following order:

`< coming soon >`

### Automated

When used automatically alias in conjunction with both `compiler-maven-extension` and `r4m-maven-extension`
the goals will be ran in the order defined in the `compiler lifecycle`.
The `compiler lifecycle` is forked twice during the lifetime of the build cycle.
By default the project is forked first during the `compile phase` where it will be executed with the `default execution`,
the second fork will be created during the `test-compile phase` where it will be executed with the `test execution`.

The exact point of execution is defined in the plugin internal `pem.xml` (Project Execution Model) file,
which is added to maven with the `r4m-maven-extension`.

You may modify/extend the build-cycle by overriding the `pem.xml` configuration with a project-specific one.
A template file can be generated from the already existing project by executing one of r4m's help goals:

> Additional Help Goals may be accessed by executing: `r4m:help`

**Generate a PEM with only the relevant executions (packaging specific)**
```bash
mvn r4m:gen-rel-pem
```

## Requirements

+ Maven 3.8
+ gcc preinstalled (at the moment)

## Optional Connectors

+ compiler-maven-extension
+ r4m-maven-extension

