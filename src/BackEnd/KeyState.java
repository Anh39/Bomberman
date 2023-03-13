package BackEnd;

/**
 * Đối tượng trạng thái của bàn phím
 * Dùng để lưu trạng thái các phím
 */
public class KeyState {
    // Mã của 1 phím
    private int keyCode;
    // Trạng thái của phím. true = đang nhấn , false = đã thả
    private boolean state = false;
    public KeyState(){

    }
    public KeyState(int keyCode) {
        this.keyCode = keyCode;
    }

    public void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }

    public int getKeyCode() {
        return keyCode;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public boolean getState() {
        return state;
    }
}
