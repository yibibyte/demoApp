#!/bin/bash

# Логика для создания резервной копии, включая выбор директории и настройку имени
# Например, можно использовать команду tar для создания архива резервной копии
tar -czf backup_$(date +%Y%m%d_%H%M%S).tar.gz /path/to/directory

# Формирование отчета о создании копии
echo "Backup created on $(date)" >> backup_report.txt
du -sh /path/to/backup >> backup_report.txt
