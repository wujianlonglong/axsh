#!/bin/bash
echo stop application
source stop.sh
echo start application
source start.sh
tail -200f ../logs/anxian-gateway-admin/anxian-gateway-admin.log
