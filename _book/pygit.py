#!/usr/bin/python //告诉编译器这是一个pyhton脚本
# 最新版在 /Users/yangzichao/OneDrive/pybash
import subprocess, sys


def git_autopush(message):  # 自动同步所有的pybash内容
    commands = [
        ['rm','-r','__pycache__'],
        ['git','add','-f','.'],

        ['git','commit','-m',message],
        ['git','push']
    ]
    for line in commands:
        print(line)
        subprocess.call(line)

message = " "
counter = 1;
while(counter < len(sys.argv)):
    message += " "+sys.argv[counter]
    counter += 1;

git_autopush(message)
