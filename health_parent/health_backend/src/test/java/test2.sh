#!/bin/bash

# 定义函数 stat_scores
# 这个函数会接受用户输入的学号，根据学号查询每个文件中包含该学号的记录，提取分数并按照从低到高排序，然后输出第几次实验和成绩
stat_scores() {
    # 提示用户输入学号
    read -p "请输入学号：" sid

    # 获取包含学号的文件列表
    files=$(grep -l "$sid" /opt/linuxer/score/*)

    # 创建一个空数组用于存储成绩
    scores=()

    # 遍历文件列表
    for file in $files; do
        # 提取文件名中的实验编号
        experiment=$(basename "$file" | cut -d 'b' -f 2)

        # 从文件中提取学号和分数
        line=$(grep "$sid" "$file")
        score=$(echo "$line" | cut -d ',' -f 2)

        # 计算星号的数量
        stars=$(printf "%${score}s" | tr ' ' '*')

        # 将实验编号、分数和星号数量添加到 scores 数组中
        scores+=("$experiment,$score,$stars")
    done

    # 使用 printf 和 sort 命令按照成绩从低到高排序，并输出实验编号、成绩和相应数量的星号
    sorted_scores=($(printf "%s
" "${scores[@]}" | sort -t ',' -k2n))
    for item in "${sorted_scores[@]}"; do
        printf "第%2s次实验: %s
" "$(cut -d ',' -f 1 <<< "$item")" "$(cut -d ',' -f 2- <<< "$item")"
    done
}

# 调用函数 stat_scores
stat_scores
