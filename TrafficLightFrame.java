import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;

public class TrafficLightFrame extends JFrame {

    public TrafficLightFrame() {
        setTitle("Traffic Light");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500); // Увеличил размер окна для удобства
        add(new TrafficLightPanel());
        setLocationRelativeTo(null); // Центрирование окна на экране
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TrafficLightFrame frame = new TrafficLightFrame();
            frame.setVisible(true);
        });
    }

    class TrafficLightPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Основная фигура (корпус светофора)
            g.setColor(Color.GRAY);
            int rectWidth = 150; // Ширина корпуса светофора
            int rectHeight = 300; // Высота корпуса светофора
            int rectX = (getWidth() - rectWidth) / 2; // Центрирование по горизонтали
            int rectY = (getHeight() - rectHeight) / 2; // Центрирование по вертикали
            int arcWidth = 40; // Радиус скругления углов по горизонтали
            int arcHeight = 40; // Радиус скругления углов по вертикали
            g.fillRoundRect(rectX, rectY, rectWidth, rectHeight, arcWidth, arcHeight);

            // Сегмент круга с выпуклой частью вверх (над корпусом светофора)
            int arcDiameter = 80; // Диаметр сегмента
            int arcX = (getWidth() - arcDiameter) / 2; // Центрирование по горизонтали
            int arcY = rectY - arcDiameter / 2 - 5; // Отступ вверх от корпуса
            g.setColor(Color.GRAY); // Цвет сегмента совпадает с основной фигурой
            Arc2D.Double arc = new Arc2D.Double(arcX, arcY, arcDiameter, arcDiameter, 0, 180, Arc2D.OPEN);
            Graphics2D g2d = (Graphics2D) g;
            g2d.fill(arc);

            // Черта снизу (нижняя граница сегмента)
            int lineY = arcY + arcDiameter; // Y-координата нижней границы
            g2d.setColor(Color.GRAY); // Цвет черты совпадает с цветом сегмента
            g2d.drawLine(arcX, lineY, arcX + arcDiameter, lineY);

            // Светофорные огни
            int lightDiameter = 60; // Диаметр огней
            int lightSpacing = 20; // Отступ между огнями
            int lightX = rectX + (rectWidth - lightDiameter) / 2; // X-координата огней
            int startY = rectY + lightSpacing; // Начальная Y-координата первого огня

            // Центрирование огней внутри корпуса светофора
            int centerX = lightX + lightDiameter / 2;

            // Красный огонь
            g.setColor(Color.RED);
            g.fillOval(centerX - lightDiameter / 2, startY, lightDiameter, lightDiameter);

            // Желтый огонь
            g.setColor(Color.YELLOW);
            g.fillOval(centerX - lightDiameter / 2, startY + lightDiameter + lightSpacing, lightDiameter, lightDiameter);

            // Зеленый огонь
            g.setColor(Color.GREEN);
            g.fillOval(centerX - lightDiameter / 2, startY + 2 * (lightDiameter + lightSpacing), lightDiameter, lightDiameter);



            // Треугольники (или стрелочки) справа от корпуса светофора
            int triangleWidth = 60; // Ширина треугольников
            int triangleHeight = 60; // Высота треугольников
            int triangleSpacing = 40; // Отступ между треугольниками
            int triangleXRight = rectX + rectWidth + triangleSpacing + 25; // X-координата треугольников справа с отступом
            int triangleXLeft = rectX - triangleSpacing - triangleWidth + 35; // X-координата треугольников слева с отступом
            int startYTriangles = rectY + (rectHeight - 3 * triangleHeight - 2 * triangleSpacing) / 2; // Начальная Y-координата первого треугольника


// Рисуем три треугольника с углом 90 градусов справа
            g.setColor(Color.GRAY);
            int[] yPoints = new int[3]; // Создаем массив для Y-координат треугольников
            for (int i = 0; i < 3; i++) {
                int triangleY = startYTriangles + i * (triangleHeight + triangleSpacing); // Y-координата текущего треугольника
                yPoints[0] = triangleY;
                yPoints[1] = triangleY;
                yPoints[2] = triangleY + triangleHeight;
                int[] xPointsRight = {triangleXRight, triangleXRight - triangleWidth, triangleXRight - triangleWidth};
                g.fillPolygon(xPointsRight, yPoints, 3);
            }

// Рисуем три треугольника с углом 90 градусов слева и зеркалируем их в другую сторону
            for (int i = 0; i < 3; i++) {
                int triangleY = startYTriangles + i * (triangleHeight + triangleSpacing); // Y-координата текущего треугольника
                yPoints[0] = triangleY;
                yPoints[1] = triangleY;
                yPoints[2] = triangleY + triangleHeight;
                int[] xPointsLeft = {triangleXLeft, triangleXLeft + triangleWidth, triangleXLeft + triangleWidth};
                g.fillPolygon(xPointsLeft, yPoints, 3);
            }
// Ножка для светофора (вертикальная линия)
            int poleWidth = 20; // Ширина ножки
            int poleHeight = 100; // Высота ножки
            int poleX = rectX + rectWidth / 2 - poleWidth / 2; // X-координата ножки
            int poleY1 = rectY + rectHeight + 5 ; // Начальная Y-координата ножки (снизу корпуса)
            int poleY2 = poleY1 + poleHeight; // Конечная Y-координата ножки

            g.setColor(Color.GRAY);
            g.fillRect(poleX, poleY1, poleWidth, poleHeight); // Рисуем вертикальную ножку

        }
        }
    }
