package ch04动手实现队列和栈;

class RingBuffer {
    private byte[] buffer;
    // mask 用于防止索引越界
    private int mask;
    // 读写指针的位置
    private int r, w;
    // 记录可以读取的字节个数
    private int size;
    // 默认初始化的 buffer 大小
    private static final int INIT_CAP = 1024;

    public RingBuffer() {
        this(INIT_CAP);
    }

    public RingBuffer(int cap) {
        // 将输入的 cap 变成 2 的指数
        cap = ceilToPowerOfTwo(cap);

        // 如果保证 capacity 是 2 的指数，
        // (i + n) % capacity 等价于 (i + n) & mask
        mask = cap - 1;
        buffer = new byte[cap];

        // 读/写指针初始化在索引 0
        r = w = 0;
        // 还没有写入任何数据，可读取字节数为 0
        size = 0;
    }

    // 从 a_04动手实现队列和栈.RingBuffer 中读取元素到 out 中，返回读取的字节数
    public int read(byte[] out) {
        if (out == null || out.length == 0 || isEmpty())
            return 0;

        // 读取的字节数
        int n = Math.min(size, out.length);

        // 情况1： r----w
        if (w > r) {
            // r----w 读取后变成 **r--w
            // copy data[r..r+n] to out[0..]
            System.arraycopy(buffer, r, out, 0, n);
            // 向前移动读指针
            r += n;
            // 可读取的字节数减少了 n
            size -= n;
            return n;
        }

        // 情况2：--w  r---
        if (r + n <= buffer.length) {
            // 情况2.1：--w  r--- 读取后变成 --w  **r-
            // copy data[r..r+n] to out[0..]
            System.arraycopy(buffer, r, out, 0, n);
        } else {
            // 情况2.2：----w  r-- 读取后变成  *r--w  ***
            int n1 = buffer.length - r;
            int n2 = n - n1;
            // copy data[r..] to out[0..n1]
            System.arraycopy(buffer, r, out, 0, n1);
            // copy data[0..n2] to out[n1..]
            System.arraycopy(buffer, 0, out, n1, n2);
        }

        // 向前移动读指针
        r = (r + n) & mask;

        // 可读取的字节数减少了 n
        size -= n;
        return n;
    }

    // 将 in 中的数据写入 a_04动手实现队列和栈.RingBuffer，返回写入字节的个数
    public int write(byte[] in) {
        if (in == null || in.length == 0)
            return 0;

        final int n = in.length;
        // 还未使用的字节数量
        int free = buffer.length - size;
        if (n > free) {
            // 确保 buffer 容量足够
            ensureCapacity(length() + n);
        }

        /*for (int i = 0; i < n; i++) {
            buffer[(w + i) & mask] = in[i];
        }*/

        // 情况1：r---w，还要细分两种情况
        if (w >= r) {
            // 情况1.1：r---w 写入后变成 r---**w
            if (buffer.length - w >= n) {
                // copy in[0..] to data[w..w+n]
                System.arraycopy(in, 0, buffer, w, n);
            } else {
                // 情况1.2：r---w 写入后变成 **w  r---*
                int n1 = buffer.length - w;
                int n2 = n - n1;
                // copy in[0..n1] to data[w..]
                System.arraycopy(in, 0, buffer, w, n1);
                // copy in[n1..] to data[0..n2]
                System.arraycopy(in, n1, buffer, 0, n2);
            }
        } else {
            // 情况二：--w   r--
            // buffer 容量肯定足够，所以直接 copy 就行了
            System.arraycopy(in, 0, buffer, w, n);
        }
        // 向前移动 w 指针
        w = (w + n) & mask;

        // 可读取的字节数增加了 n
        size += n;
        return n;
    }

    // 返回可读的字节数量
    public int length() {
        return size;
    }

    // 没有可读的数据
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity(int newCap) {
        newCap = ceilToPowerOfTwo(newCap);
        // 将 data 中的数据读入 temp 中
        byte[] temp = new byte[newCap];
        int n = read(temp);
        // 更新其他字段的值
        this.buffer = temp;
        this.r = 0;
        this.w = n;
        this.mask = newCap - 1;
    }

    // 将输入的 n 转化为 2 的指数，比如输入 12，返回 16
    private static int ceilToPowerOfTwo(int n) {
        if (n < 0) {
            // 肯定不能小于 0
            n = 2;
        }

        if (n > (1 << 30)) {
            // int 型最大值为 2^31 - 1
            // 所以无法向上取整到 2^31
            n = 1 << 30;
        }

//        int res = 1;
//        while (res < n) {
//            res = res * 2;
//        }
//        return res;

        // 位运算技巧，参考如下链接：
        // http://graphics.stanford.edu/~seander/bithacks.html#RoundUpPowerOf2
        n--;
        n |= n >> 1;
        n |= n >> 2;
        n |= n >> 4;
        n |= n >> 8;
        n |= n >> 16;
        n++;

        return n;
    }

    public static void main(String[] args) {
        RingBuffer rb = new RingBuffer(3);

        String s = "abcdefghijklmn";
        int nwrite = rb.write(s.getBytes());
        System.out.println("write " + nwrite + " bytes " + s);

        byte[] out = new byte[9];
        int nread = rb.read(out);
        System.out.println("read " + nread + " bytes " + new String(out));

        nread = rb.read(out);
        System.out.println("read " + nread + " bytes " + new String(out));

        //write 14 bytes abcdefghijklmn
        //read 9 bytes abcdefghi
        //read 5 bytes jklmnfghi
    }
}