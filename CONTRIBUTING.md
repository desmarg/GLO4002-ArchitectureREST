# <a name="contributing"></a> Contributing

## <a name="branch"></a> Branch Guidelines

### master
This branch is only staged before a release.

### dev
This branch is the main branch. It contains only stable code that was merged
via a PR.

### feature branches
Feature branches are named according to the user story they are corresponding.
For instance, [this user story](http://projet2018.qualitelogicielle.ca/carnet/COOU_ouvrir_compte)
whould be named `feature/COOU`. The branch can be created via `git checkout -b feature/COOU.

## <a name="commit"></a> Commit Guidelines

### Command
Please use `git commit` instead of `git commit -m` since it is easier to write
proper commit messages. Note that the default git text editor is `vim`.
To change the default editor use `git config --global core.editor "nano"`,
where `nano` is an example editor. If you are inside the `vim` editor,
write `:q!` to exit it.

### Message
Each commit message should consists of a **subject**,
a **body** and a **issue ref** if it is a bug fix.

```
{{subject}}
<BLANK LINE>
{{ body }}
<BLANK LINE>
{{ issue ref }}
```

The **subject** should be less than 50 characters and should describe the commit
in general.

The **body** is only required should the **subject** require no further
explanation. Otherwise the **body** is used to describe the commit in more details.
Each line of the body cannot be longer than 80 characters.

Finally, if the commit is a code fix, the footer should contain a
[closing reference to an issue](https://help.github.com/articles/closing-issues-via-commit-messages/), if any.

#### Example Commit :

```
Derezz the master control program

MCP turned out to be evil and had become intent on world domination.
This commit throws Tron's disc into MCP (causing its deresolution)
and turns it back into a chess game.
```

See [here](https://chris.beams.io/posts/git-commit/) for more examples.

## <a name="pr"></a> Submitting a Pull Request

### Before the PR is Created
1. Make sure your PR is not a duplicate of another open PR.
1. Make sure your branch follows our [branch guidelines](#branch).
1. Make sure your PR contains a unic test for each required critera
(and that they pass).
1. (Optional / Advanced) [Rebase your branch](#rebase) using a commit message that follows our
[commit guidelines](#commit).
### After the PR is Created
1. Announce the PR on telegram.
### While the Code is Reviewed
1. Make sure you are available in case your code some requires modifications.
### After the Code Review
1. (Optional / Advanced) [Rebase your branch](#rebase) once again, if modifications
were made.
### After the PR is merged
1. Delete the remote branch as well as your local one.

## <a name="rebasing"></a> Rebasing

Rebasing is very helpfull to facilitate code review and to
keep our commit history simple. It is recommanded to rebase incremental
commits by squashing them. We can also modify the commit messages
if they not descriptive enought.
See [this](https://www.atlassian.com/git/tutorials/merging-vs-rebasing)
for more information.

