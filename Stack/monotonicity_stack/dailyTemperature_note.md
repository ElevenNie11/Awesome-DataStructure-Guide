i = 0 时：stack[0] = 0, top = 0

---

i = 1 时：while里的条件：top >= 0 && temperatures[1] > temperatures[stack[0]]，即 74 > 73，成立

          previousDate = stack[0] = 0, top--，top = -1

          answer[0] = 1 - 0 = 1

---

i = 2 时：while里的条件不成立，所以 stack[++top] = 2, top = 0, 即 stack[0] = 2

75 则留在栈里不出栈

---

i = 3 时：while里的条件：top >= 0 && temperatures[3] > temperatures[stack[0]]，即 71 > 75，不成立

          stack[++top] = 3, top = 1, 即 stack[1] = 3

71 则留在栈里不出栈

---

i = 4 时：while里的条件：top >= 0 && temperatures[4] > temperatures[stack[1]]，即 69 > 71，不成立

          stack[++top] = 4, top = 2, 即 stack[2] = 4

69 则留在栈里不出栈

从栈底到栈顶顺序：75(2) 71(3) 69(4)

---

i = 5 时：while里的条件：top >= 0 && temperatures[5] > temperatures[stack[2]]，即 72 > 69，成立

          previousDate = stack[2] = 4, top--，top = 1

          answer[4] = 5 - 4 = 1

69 则出栈，71 75则留在栈里不出栈

i = 5 时：while里的条件：top >= 0 && temperatures[5] > temperatures[stack[1]]，即 72 > 71，成立

          previousDate = stack[1] = 3, top--，top = 0

          answer[3] = 5 - 3 = 2

71 则出栈，75则留在栈里不出栈

i = 5 时：while里的条件：top >= 0 && temperatures[5] > temperatures[stack[0]]，即 72 > 75，不成立

          stack[++top] = 5, top = 1, 即 stack[1] = 5
72 则留在栈里不出栈（栈里还有75）

---

i = 6 时：while里的条件：top >= 0 && temperatures[6] > temperatures[stack[1]]，即 76 > 72，成立

          previousDate = stack[1] = 5, top--，top = 0

          answer[5] = 6 - 5 = 1

i = 6 时：while里的条件：top >= 0 && temperatures[6] > temperatures[stack[0]]，即 76 > 75，仍然成立

         previousDate = stack[0] = 2, top--，top = -1

         answer[2] = 6 - 2 = 4

i = 6 时：while里的条件不成立，所以 stack[++top] = 6, top = 0, 即 stack[0] = 6

76 则留在栈里不出栈

---

i = 7 时：while里的条件：top >= 0 && temperatures[7] > temperatures[stack[0]]，即 73 > 76，不成立

          stack[++top] = 7, top = 1, 即 stack[1] = 7

73 则留在栈里不出栈

最终栈里还有76和73，说明这两天之后没有更高温度，所以answer[6] = 0, answer[7] = 0