#!/bin/bash

# 定义函数sort_scores
# 这个函数会读取一个实验编号，然后根据实验编号的文件中的成绩进行排序，并输出排名前10位的学生
sort_scores(){
  # 提示用户输入实验编号
  read -p "请输入实验编号：" lab
  
  # 构建指定实验编号的文件路径
  file="/opt/linuxer/score/lab${lab}"
  
  # 创建一个空数组来存储成绩
  scores=()
  
  # 读取文件中的每一行
  while IFS=',' read -r sid mark rest; do
    # 将sid和mark添加到scores数组中
    scores+=("$sid,$mark")
  done < "$file"
  
  # 使用printf和sort命令对成绩进行排序，并将排序后的结果存储在sorted_scores数组中
  sorted_scores=($(printf "%s
" "${scores[@]}" | sort -t',' -k2 -nr))
  
  # 遍历排序后的成绩数组，并输出每个学生的成绩和排名
  rank=1
  for s in "${sorted_scores[@]:0:10}"; do
    # 获取学生的成绩和排名
    IFS=',' read -r sid mark <<< "$s"
    
    # 计算星号的数量
    stars=$(printf "%.0s*" $(seq 1 "$mark"))
    
    # 输出学生的成绩和排名，以及相应数量的星号
    printf "%s, 排名：%2d, 成绩：%s, %s
" "$sid" "$rank" "$mark" "$stars"
    
    ((rank++))
  done
}

# 调用函数sort_scores
sort_scores
