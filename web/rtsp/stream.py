from flask import Flask, render_template, Response
from PIL import Image
import cv2
import pathlib
import os
import time
from natsort import natsorted

app = Flask(__name__)

imagesDirPath = '/home/h1w/Dev/Cyber_Garden_Winter/images'

# img_list = []
# for filepath in pathlib.Path('images').glob('**/*'):
#     img_list.append(str(filepath.absolute()))

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/onlystream')
def onlystream():
    return render_template('stream.html')

def gen():
    while True:
        dirFiles = natsorted(os.listdir(imagesDirPath))
        if len(dirFiles) >= 1:
            pathToFirstFrame = os.path.join(imagesDirPath, dirFiles[0])
            frame = cv2.imread(pathToFirstFrame, 1)
            frame = cv2.imencode('.jpg', frame)[1].tobytes()
            time.sleep(1.0)
            os.remove(pathToFirstFrame)
            yield (b'--frame\r\n'
                    b'Content-Type: image/jpeg\r\n\r\n' + frame + b'\r\n\r\n')

@app.route('/video_feed')
def video_feed():
    return Response(gen(), mimetype='multipart/x-mixed-replace; boundary=frame')

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=45005, debug=True)