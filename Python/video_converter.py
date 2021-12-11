# В основном нужна библиотека Moviepy
import os
f = open('movies.txt', 'w')

# i = 0
# for file in files:
#     os.rename(file, str(i)+'.avi')
#     i = i+1

result = os.listdir("C:\\Users\\User\\Desktop\\dataset")
temp=[]

for i in range(len(result)-2):
    temp.append(int(result[i].strip(".avi")))
temp.sort()

for i in range(len(result)-2):
    f.write("file '../" + str(temp[i]) + ".avi'\n")

f.close()
os.system("ffmpeg -f concat -safe 0 -i movies.txt -c copy video_draft.avi")

# https://stackoverflow.com/questions/38996925/ffmpeg-concat-unsafe-file-name
# https://stackoverflow.com/questions/15186500/howto-merge-two-avi-files-using-ffmpeg