#!/usr/bin/python //告诉编译器这是一个pyhton脚本
# 最新版在 /Users/yangzichao/OneDrive/pybash
import subprocess, sys


def git_autopush(message):  # 自动同步所有的pybash内容
    commands = [
        ['rm','-r','__pycache__'],
        ['git','add','.'],

        ['git','commit','-m',message],
        ['git','push']
    ]
    for line in commands:
        print(line)
        subprocess.call(line)

git_autopush(sys.argv[1])
