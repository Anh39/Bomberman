import BackEnd.DefaultParameter;
import BackEnd.SoundPlayer;
import Graphic.Graphic;


/**
 * Thứ tụ xem để dễ hiểu
 *      - DefaultParameter (Chỉnh các tham số mặc định trong đây)
 *      - Entities: Entity ->  Player,Enemy,Projectile,Buff -> New
 *      - BackEnd: KeyState -> MyClock -> Physics -> MainProcess
 *      - Graphics: StatusBar,MyPanel,MyMenu,KeyBoard -> Render -> Graphic
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
 *  + JProgressBar
 *  + ImageIcon
 */

public class Main {
    public static void main(String[] args) {
        SoundPlayer.soundnen();
        // Khởi tạo game
        Graphic.startGraphic();

    }
}
/**
 * Note : Đã có thể test phần buff, chỉnh maxNumberOfBuffs trong DefaultParameter,
 * sau đó thêm trường hợp trong Main , hàm spawnBuff();
 */