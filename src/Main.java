import Graphic.Graphic;

/**
 * Thứ tụ xem để dễ hiểu
 *      - Entities: Entity ->  Player,Enemy,Projectile,Buff -> New
 *      - BackEnd: KeyState -> MyClock -> Physics -> MainProcess
 *      - Graphics: StatusBar,MyPanel,MyMenu -> Render -> Graphic
 */

/**
 * Note:
 * Các loại Implement:
 * + ActionListener :
 *      - Dùng để tính thời gian
 * + KeyListener:
 *      - Dùng để nhập dữ liệu từ bàn phím
 * + MouseListener :
 *      - Dùng để nhập dữ liệu từ chuột
 * Xử lý đồ họa gồm
 *  + JFrame
 *  + JPanel
 *  + JLabel (hitbox của các đối tượng)
 *  + JTextField
 *  + JButton
 *  + ImageIcon
 */

public class Main {
    public static void main(String[] args) {
        // Khởi tạo game
        Graphic.startGraphic();
    }
}