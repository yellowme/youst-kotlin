# Development Workflow

## Overview ✌🏻

This guide provides information about:

* Cleaning and setup
* New sprints, iterations or particular features
* Prepare code base releases
* Update working branches after release
* Add fixes or hot-fixes on release and master branches

## Workspace 🧽

Before your start working on an issue as a developer you need to ensure that your local repository is up to date.

First, is necessary to update your *develop* branch to match remote changes.

```shell
git checkout develop
```

```shell
git fetch origin
```

```shell
git pull origin develop
```

Finally, you must clean remote references to branches:

```shell
git remote prune origin
```

Also, do not forget to delete any local branch that does not exist in the remote anymore:

```shell
git branch -d <branch-name>
```

For more information about the last command, we recommend to check [this thread](http://stackoverflow.com/questions/4040717/git-remote-prune-didnt-show-as-many-pruned-branches-as-i-expected).

## Work 💪🏻

### Analyze

Before working on an issue that was assigned to you, you must review it and understand it. In case something is not clear or you have questions, make sure you ask them.

* If there is any question, you need to write a comment on the issue thread mentioning the current issue administrator or reporter.
* Wait for the clarifications.

### Branch naming 

#### Types

In general the first part of your branch name should match the type of task your working on followed by the `/` character:

* chore
* feature
* fix
* refactor
* hotfix

Examples:

* `chore/gradle-dependencies`
* `feature/recurrent-loans-view`
* `fix/login-flow`
* `refactor/calculator-base`
* `hotfix-1.0.1/user-login-redirection`

### Actually coding 

When you are ready to work on the issue, you need to create a new branch. 

As a developer you need to create a local branch using the nomenclature defined at the beginning of this section and push it to the remote repository. *It is important that the branch starts from the develop remote reference.*

```shell
git fetch origin && git checkout develop
```

```shell
git checkout -b <branch-name>
```

```shell
git push origin <branch-name>
```

After the working branch is ready, you must make the proper changes and push your work to the remote repository.

### Request a review

When you have finished working on an issue, and your working branch is already pushed to the remote repository, you will need to create a Pull Request (PR) from your working branch to the *develop* branch and assign it at least to two reviewers.

Inside the Pull Request description you must specify if the reviewer needs to read any comments made on the working branch commits. 

**Please use our [template](https://github.com/yellowme/youst-kotlin/blob/master/.github/PULL_REQUEST_TEMPLATE/pull_request.md)** for your pull request description
