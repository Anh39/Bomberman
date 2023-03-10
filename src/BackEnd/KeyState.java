package BackEnd;

public class KeyState {
    private int keyCode;
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
