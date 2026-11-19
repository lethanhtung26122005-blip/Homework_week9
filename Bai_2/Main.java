package Homework.Homework_week9.Bai_2;
// Product
interface Notification {
    void send(String msg);
}

// ConcreteProduct
class EmailNotification implements Notification {
    @Override
    public void send(String msg) {
        System.out.println("[EMAIL] Gui email: " + msg);
    }
}

class SmsNotification implements Notification {
    @Override
    public void send(String msg) {
        System.out.println("[SMS] Gui tin nhan: " + msg);
    }
}

// Creator
abstract class NotificationApp {
    // Factory method: lớp con quyết định loại Notification
    protected abstract Notification createNotification();

    // Không tạo trực tiếp đối tượng, chỉ gọi factory method
    public void notifyUser(String msg) {
        Notification notification = createNotification();
        notification.send(msg);
    }
}

// ConcreteCreator
class EmailApp extends NotificationApp {
    @Override
    protected Notification createNotification() {
        return new EmailNotification();
    }
}

class SmsApp extends NotificationApp {
    @Override
    protected Notification createNotification() {
        return new SmsNotification();
    }
}

// Chương trình chính
public class Main {
    public static void main(String[] args) {
        NotificationApp app = new EmailApp();
        app.notifyUser("Don hang cua ban da duoc xac nhan!");

        app = new SmsApp();
        app.notifyUser("Ma OTP cua ban la 123456");
    }
}