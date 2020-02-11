#!/usr/bin/env bash

eval $(ssh-agent -s)
pass=$(perl -le 'print $ENV{"froala.git.key-password"}')
expect << EOF
  spawn ssh-add /root/.ssh/github
  expect "Enter passphrase"
  send "$pass\r"
  expect eof
EOF
#echo $(ssh-add -l)

git push -f