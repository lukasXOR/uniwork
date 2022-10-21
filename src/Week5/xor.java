package Week5;
class XOR {
    int keys[];
    XOR(int keys[]) {
        this.keys = keys;
    }
    int[] encrypt (int data[]) {
        int[] encData = {};
        for (int i = 0; i < data.length; i++) {
            encData[i] = this.invertKey(data[i] ^ this.keys[i]);
        }
        this.rotateKeys();
        return encData;
    }
    void rotateKeys() {
        for (int i = 0; i < this.keys.length; i++) {
            this.keys[i] = this.invertKey((this.keys[i] * 17) & 0xFF);
        }
    }
    int invertKey(int key) {
        return (key << 4 | key >> 4) & 0xFF;
    }
    void setKeys(int keys[]) {
        this.keys = keys;
    }
    void log() {
        for (int key: keys){
            System.out.println(key);
        }
    }
}
public class xor {
    public static void main(String[] args) {
        int keys[] = {3, 5, 6};
        XOR auth = new XOR(keys);
        auth.log();
    }
}
