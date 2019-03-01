import matplotlib.pyplot as plt
import sys

a = int(sys.argv[1])
b = int(sys.argv[2])

x_coord = [float(x) for x in sys.argv[3].lstrip('[').rstrip(']').split(', ')]
y_coord = [float(x) for x in sys.argv[4].lstrip('[').rstrip(']').split(', ')]
print("calling python function with parameters:")

fig, ax = plt.subplots(nrows=1, ncols=1, figsize=(16, 9))
print(sys.argv[1])

ax.plot(x_coord, y_coord)
plt.show()
