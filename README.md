This repository contains the Java code for our competition robot and for several prototype robots we use to test and prototype use of the WPILib library, and to assist the electrical and mechanical teams when they need to use the Smart Desktop (or SFX) to experiment with sensors and actuators.

### Get set up

If you already have [your development environment](https://github.com/frc-4931/2014/wiki/Java) set up (e.g., installed Git, Eclipse, Ant, etc), you'll want to fork this repository and then clone your fork:

```
$ cd ~/frc
$ git clone https://github.com/{your_username}/2014-Robot fork-2014-Robot
$ cd fork-2014-Robot
$ git remote add upstream https://github.com/frc-4931/2014-Robot.git
$ git fetch upstream
$ git branch --set-upstream-to=upstream/master master
$ git pull upstream master
```

Your local Git repository is now connected to both your fork (e.g., `origin`) and the team's shared repository (e.g., `upstream`).

Import the projects in your `fork-2014-Robot` directory into Eclipse, as described [here](https://github.com/frc-4931/2014/wiki/Java#set-up-eclipse).

### Make your changes

As with the other repository, you'll follow the same [development process](https://github.com/frc-4931/2014/wiki/Java-Development-Steps) for each of the issues you'll work on. The basics are:

#### Create an issue

Create an issue that describes your task in the [2014-Design](https://github.com/frc-4931/2014-Design/issues) repository.

#### Update your 'master' branch

```
$ git checkout master
$ git pull upstream
```

#### Create a topic branch

```
$ git checkout -b issue-999
```

#### Make and test your changes for the issue

Make your changes to the code in Eclipse (which compiles automatically upon save), and optionally compile using the command line:

```
$ ant compile
```

If possible, test your changes on the robot (first make sure you're connected to the robot's network):

```
$ ant deploy run
```

#### Commit your changes

Once you're happy with your changes, commit them to the history on your (local) topic branch:

```
$ git commit .
```

and then push your topic branch up to your fork:

```
$ git push origin issue-9999
```

#### Create a pull-request

In your browser go to `https://github.com/{your_username}/2014-Robot` and create a pull-request for your topic branch. See our [documentation](https://github.com/frc-4931/2014/wiki/Java-Development-Steps#step-8-push-to-github-and-create-a-pull-request) for more detail.
