#!/usr/bin/python

import argparse
import random
import sys

def toString(grid):
  out = ""
  for r in grid:
    for c in r:
      out += c
    out += '\n'
  return out[:-1]
 
def middlePoint(r1, c1, r2, c2):
  return [(r1 + r2) / 2, (c1 + c2) / 2]

def cutFrom(grid, r, c):
  if grid[r][c] != 'x':
    return grid
  candidates = [[r + 2, c],
                [r - 2, c],
                [r, c + 2],
                [r, c - 2]]
  while (len(candidates) != 0):
    candidate = random.choice(candidates)
    candidates.remove(candidate)
    try:
      if grid[candidate[0]][candidate[1]] == 'x':
        cutPoint = middlePoint(r, c, candidate[0], candidate[1])
        grid[cutPoint[0]][cutPoint[1]] = ' '
        grid[r][c] = ' '
        return grid
    except:
      pass

  grid[r][c] = ' '
  return grid
  
def generate(filename, w, h):
  # Check for valid parameters
  if w % 2 != 1 or h % 2 != 1:
    print "Error: Width and height must be odd"
    return

  # Generate filled grid
  grid = []
  for i in range(w):
    q = []
    for i in range(h):
      q.append('#')
    grid.append(q)

  # Create empty grid nodes
  for r in range(1, w, 2):
    for c in range(1, h, 2):
      grid[r][c] = 'x'

  # Iterative passes
  for r in range(1, w, 2)[::-1]:
    for c in range(1, h, 2)[::-1]:
      grid = cutFrom(grid, r, c)

  # Add the start and end
  grid[1][1] = 'S'
  grid[w - 2][h - 2] = 'E'

  filename.write(toString(grid))

  print toString(grid)
  print "written to " + filename.name

def main():
  parser = argparse.ArgumentParser(description='This script generates a maze')
  parser.add_argument('filename', type=argparse.FileType('w'),
                      help='the file to write the maze to')
  parser.add_argument('width', type=int,
                      help='the width of the maze, must be odd')
  parser.add_argument('height', type=int,
                      help='the height of the maze, must be odd')
  args = parser.parse_args();
  generate(args.filename, args.width, args.height)

if __name__ == "__main__":
  main()
