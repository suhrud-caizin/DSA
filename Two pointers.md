

---

## 🔹 Sliding Window / Two Pointer Patterns

### **Pattern 1: Constant Window**

- Used when the **window size is fixed** (e.g., length = `k`).
    
- Example: _Maximum average of subarray of size `k` (LeetCode 643)_.
    
- Process:
    
    1. Compute the first window of size `k`.
        
    2. Slide the window → add one element, remove one element.
        
    3. Track max/min/whatever you need.
        

---

### **Pattern 2: Dynamic Window**

- Used when the **window size is variable**, and you’re trying to satisfy some condition.
    
- Examples:
    
    - _Longest subarray with sum ≤ 12_.
        
    - _Longest substring with at most K distinct characters_.
        

#### Sub-patterns inside dynamic window:

1. **Longest subarray/substring where <>**
    
    - Expand the right pointer to grow the window.
        
    - If the condition breaks, shrink from the left until valid again.
        
    - Track the maximum valid length.
        
    - Example: _Longest substring with at most 2 distinct characters_.
        
2. **Number of subarrays where <>**
    
    - Similar to above, but instead of only max length, count all valid windows.
        
    - Example: _Count subarrays with product less than k (LC 713)_.
        
3. **Shortest window where <>**
    
    - Expand until condition is satisfied.
        
    - Once valid, shrink from the left to minimize size.
        
    - Track the minimum length.
        
    - Example: _Minimum size subarray sum ≥ target (LC 209)_.
        
    
    👉 Key idea: **Once a valid window is obtained → shrink from the left** to find the shortest.
    

---

## ✅ Quick Mental Map

- **Fixed size** → Pattern 1.
    
- **Variable size (expand/shrink depending on condition)** → Pattern 2.
    
- Within Pattern 2:
    
    - Longest window → expand, shrink only when invalid.
        
    - Count subarrays → count each time window is valid.
        
    - Shortest window → shrink aggressively once valid.
        

---

Would you like me to **draw a visual diagram (ASCII/boxes)** showing how left/right pointers move for each pattern?