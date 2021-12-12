import cv2
import subprocess as sp
import glob
import os
import numpy as np

img_width = 640
img_height = 480

import pathlib
img_list = []
for filepath in pathlib.Path('images').glob('**/*'):
    img = cv2.imread(str(filepath.absolute()), 1)
    # img = cv2.imencode('.png', img)[1]
    # img = cv2.resize(img, (640, 480), interpolation = cv2.INTER_AREA)
    img_list.append(img)

ffmpeg_cmd = 'ffmpeg'
ffplay_cmd = 'ffplay'

# sp.run([ffmpeg_cmd, '-y', '-f', 'lavfi', '-i', f'testsrc=size={img_width}x{img_height}:rate=1:duration=10', 'images/image%04d.jpg'])

img_list_len = len(img_list)
img_index = 0

fps = 5

rtmp_url = 'rtmp://0.0.0.0:34567/stream'

command = [ffmpeg_cmd,
'-y',
'-f', 'rawvideo',
'-vcodec', 'rawvideo',
'-s', f'{img_width}x{img_height}',
'-pix_fmt', 'rgb24',
'-r', f'{fps}',
'-i', '-',
'-pix_fmt', 'yuvj444p',
'-c:v', 'libx264',
'-preset', 'ultrafast',
'-f', 'flv',
rtmp_url
]

process = sp.Popen(command, stdin=sp.PIPE)

while True:
    current_img = img_list[img_index]
    img_index = (img_index+1) % img_list_len

    process.stdin.write(current_img)

p.stdin.close()
p.wait()