# Pixel Pilot
A top down dogfighting game with pixel graphics. Pew Pew!

Such game, many flight, very plane, wow.
The Wright brothers are super proud.

![The Pixel Pilot mascot](http://i.imgur.com/EhxqixE.jpg)
# PR Workflow

1. Open feature branch
 * e.g. `git checkout -b <feature-name>`
2. Work on feature branch (push often!)
3. Squash temporary commits together
 * Use `git rebase -i HEAD~n` to rebase last `n` commits from HEAD
 * You'll need to force push to to your feature branch: `git push --force origin <feature-name>`
4. Open PR on Github and do code review
    * **NEVER CLICK THE SHINY GREEN BUTTON**!
5. Make changes from code review
    * Squash changes from code review into commits
    * Rebase and force push!
6. When ready to merge into Master, update feature branch with Master
    * Update local master
        1. `git checkout master`
        2. `git pull origin master`
        2. `git checkout <feature-name`>
    * Rebase Master onto feature branch
        1. `git rebase master`
        2. Fix merge conflict (if needed)
        3. `git push --force origin <feature-name>`
7. Merge updated feature branch into Master
    * `git checkout master`
    * `git merge <feature-branch>`
    * `git push origin master`
