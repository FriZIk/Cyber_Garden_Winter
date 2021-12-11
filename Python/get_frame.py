import cv2
import numpy as np
import time

cap = cv2.VideoCapture('video_draft.avi')

start_time = time.time()
i = 0
while(cap.isOpened()):
    ret, frame = cap.read()

    if time.time() - start_time >= 1: 
        cv2.imwrite("data/"+str(i) + ".png", frame)
        start_time = time.time()
        print("Ещё кадр пошёл!")
        i+=1

    if cv2.waitKey(25) & 0xFF == ord('q'):break
    cv2.imshow('Frame',frame)

cap.release()
cv2.destroyAllWindows()