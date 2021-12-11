import numpy as np
import cv2
import matplotlib.pyplot as plt
import imutils
# read video
cap = cv2.VideoCapture('video_draft.avi')

if (cap.isOpened()== False):
  print("Error opening video stream or file")

firstFrame = None

while(cap.isOpened()):
    ret, frame = cap.read()

    if frame is None:
      break
    
    # Пытаемся внедрить расопзнавание:
    frame = imutils.resize(frame, width=500)
    gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    gray = cv2.GaussianBlur(gray, (21, 21), 3)

    if firstFrame is None:
      firstFrame = gray
      continue

    frameDelta = cv2.absdiff(firstFrame, gray)
    thresh = cv2.threshold(frameDelta, 25, 255, cv2.THRESH_BINARY)[1]

    # Находим контуры
    thresh = cv2.dilate(thresh, None, iterations=2)
    cnts = cv2.findContours(thresh.copy(), cv2.RETR_EXTERNAL,cv2.CHAIN_APPROX_SIMPLE)
    cnts = imutils.grab_contours(cnts)

    # Выбираем достаточно большие из них

    for c in cnts:
      if cv2.contourArea(c) < 2000:
        continue
      # Рисуем контур
      (x, y, w, h) = cv2.boundingRect(c)

      roi = frame[y: y + h, x: x + w]
      cv2.imwrite("test/"+str(i) + ".png", roi)
      i+=1
      
      cv2.rectangle(frame, (x, y), (x + w, y + h), (0, 255, 0), 2)

    if ret == True:
        cv2.imshow('Frame',gray)
        cv2.imshow('Normal',frame)
        if cv2.waitKey(25) & 0xFF == ord('q'):break
    else: break

cap.release()
cv2.destroyAllWindows()