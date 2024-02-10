#!/bin/bash

# Логика для создания резервной копии, включая выбор директории и настройку имени
# Например, можно использовать команду tar для создания архива резервной копии
tar -czf backup_$(date +%Y_%m_%d_%H_%M_%S).tar.gz /path/to/backup_directory

# Формирование отчета о создании копии
echo "Backup created on $(date)" >> backup_report.txt
du -sh /path/to/backup_directory >> backup_report.txt
