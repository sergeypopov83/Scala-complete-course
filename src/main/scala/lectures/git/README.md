# Git tasks

### 1. Исправляем опечатку

Ситуация:

```
echo Very important data > file.txt
git add file.txt
git commit -m 'Very impotant commit'
```

Перечитав комментарий, вы вдруг обнаруживаете опечатку в слове `important`. Напишите внизу команду, которая исправит это досадное недоразумение:

```
Write your solution here
```

### 2. Восстанавливаем наглядность

Ситуация:

```
echo Very important data > file.txt
git add file.txt
git commit -m 'Very important commit'
echo Second part of important data >> file.txt
git commit -a --amend
```

Тут вы понимаете, что зря вы все объединили в один коммит и надо бы их все-таки разбить на два коммита. Напишите команды, которые это исправят:

```
Write your solution here
```

### 3. Жонглируем коммитами

В конце рабочего дня вы пишете:

```
echo Not so important data > fileB.txt
git add fileB.txt
git commit -m 'Commited fileB.txt'
echo Very important data > fileA.txt
git add fileA.txt
git commit -m 'Commited fileA.txt'
```

С чувством выполненного долга вы выключаете компьютер и идете домой. Но вы всю ночь ворочаетесь и не можете уснуть, потому что ваш внутренний перфекционист очень недоволен тем, что `fileB` был закоммичен раньше, чем `fileA`. Очень хорошо, что накануне вы внимательно слушали лекции про git и теперь точно знаете, как это можно исправить. Даже не позавтракав, вы прибегаете на работу, включаете компьютер и пишете следующие команды:

```
Write your solution here
```

### 4. Слишком подробно

Ситуация:

```
echo Vitsin > Trus.txt
git add fileA.txt && git commit -m 'Commited Trus'
echo Nikulin > Balbes.txt
git add fileA.txt && git commit -m 'Commited Balbes'
echo Morgunov > Bivaliy.txt
git add fileA.txt && git commit -m 'Commited Bivaliy'
```

Тут вы понимаете, что незачем разбивать эту знаменитую троицу на три разных коммита, да и длинная история вам ни к чему. Поэтому надо их всех соединить в один коммит с комментарием `Samogonshiki`. Ваши действия:

```
Write your solution here
```

### 5. Финальный босс

Ситуация:

```
echo Some hidden data > .hidden
git add .hidden && git commit -m 'Added hidden file'
echo .hidden > .gitignore
git add .gitignore && git commit -m 'Added gitignore'
```

И тут вы понимаете, что сделали большую ошибку, закоммитив в репозиторий файл, который там быть не должен. Как вы можете убрать его из последующих коммитов в репозитории, но оставить в рабочей копии (чтобы git его игнорировал)?

```
Write your solution here
```

