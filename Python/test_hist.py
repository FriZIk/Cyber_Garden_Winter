import cv2
import numpy as np

img = cv2.imread('1.jpg',0)
clahe = cv2.createCLAHE(clipLimit=4.0, tileGridSize=(8,8))
cl1 = clahe.apply(img)
cv2.imwrite('res.jpg',cl1)