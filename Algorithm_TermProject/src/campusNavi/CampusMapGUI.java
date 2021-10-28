package campusNavi;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;





public class CampusMapGUI extends JFrame{


   ImageIcon i = new ImageIcon("images/schoolCampus_1.jpg"); 
   Image im=i.getImage();



   int cnt=0;
   String start, destination;
   ArrayList<String> stops;
   double distance;



   public CampusMapGUI() {


      this.setTitle("Navigtor in my hand"); //frame
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


      MyPanel   panel =new MyPanel();
      panel.setLayout(null);

      JLabel label = new JLabel();   //텍스트
      label.setBounds(90,3,500,50);
      label.setText("G-Navi : BGM");
      this.getContentPane().add(label);

      /***건물 ***/
      JButton btn1 = new JButton("");//가천관
      JButton btn2 = new JButton("");//비전타워
      JButton btn3 = new JButton("");//법과대학
      JButton btn4 = new JButton("");//공과대학1
      JButton btn5 = new JButton("");//공과대학2
      JButton btn6 = new JButton("");//바이오나노대학
      JButton btn7 = new JButton("");//IT대학
      JButton btn8 = new JButton("");//바이오나노연구원
      JButton btn9 = new JButton("");//예술대학2
      JButton btn10 = new JButton("");//예술대학1
      JButton btn11 = new JButton("");//글로벌센터
      JButton btn12 = new JButton("");//교육대학원
      JButton btn13 = new JButton("");//중앙도서관
      JButton btn14 = new JButton("");//한의대
      JButton btn15 = new JButton("");//기숙사
      
      /***편의시설***/
      JButton btn16 = new JButton("");//편의점
      JButton btn17 = new JButton("");//은행
      JButton btn18 = new JButton("");//보건실

      /***출발 & 도착 ***/
      JButton btn19 = new JButton("");//출발
      JButton btn20 = new JButton("");//도착

      JButton reset = new JButton("");  //reset 버튼
      
      reset.setBounds(484,531,83,34);
      this.getContentPane().add(reset);
      reset.setContentAreaFilled( false );
      reset.setBorderPainted(false);
      
     reset.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(start!=null||destination!=null)
            stops.clear();
            dispose();
            new CampusMapGUI();
      }
      });
      
      


      btn1.setBounds(434,297,30,26);//가천관
      this.getContentPane().add(btn1);

      btn2.setBounds(334,480,29,31);//비전타워
      this.getContentPane().add(btn2);

      btn3.setBounds(387,482,27,21);//법과대학
      this.getContentPane().add(btn3);

      btn4.setBounds(188,324,24,25);//공과대학1
      this.getContentPane().add(btn4);

      btn5.setBounds(454,424,25,30);//공과대학2
      this.getContentPane().add(btn5);

      btn6.setBounds(330,296,19,16);//바이오나노대학
      this.getContentPane().add(btn6);

      btn7.setBounds(187,417,25,23);//IT대학
      this.getContentPane().add(btn7);

      btn8.setBounds(370,426,27,33);//바이오나노연구원
      this.getContentPane().add(btn8);

      btn9.setBounds(287,255,31,24);//예술대학2
      this.getContentPane().add(btn9);

      btn10.setBounds(145,253,23,26);//예술대학1
      this.getContentPane().add(btn10);

      btn11.setBounds(76,352,28,25);//글로벌센터
      this.getContentPane().add(btn11);

      btn12.setBounds(446,172,25,26);//교육대학원
      this.getContentPane().add(btn12);

      btn13.setBounds(438,118,26,28);//중앙도서관
      this.getContentPane().add(btn13);

      btn14.setBounds(401,390,23,20);//한의대
      this.getContentPane().add(btn14);

      btn15.setBounds(67,93,28,24);//기숙사
      this.getContentPane().add(btn15);

      btn16.setBounds(614,489,79,24);//편의점
      this.getContentPane().add(btn16);

      btn17.setBounds(614,519,79,24);//은행
      this.getContentPane().add(btn17);

      btn18.setBounds(614,544,79,24); //보건실
      this.getContentPane().add(btn18);

      btn19.setBounds(10,519,99,51);
      this.getContentPane().add(btn19); //출발

      btn20.setBounds(118,520,98,51);
      this.getContentPane().add(btn20);//도착



      btn1.setContentAreaFilled( false );
      btn1.setBorder(null);
      btn1.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {

         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}
         if(cnt == 1) {
            start = "가천관";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}
         if(cnt == 2) {
            destination = "가천관";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            distance =dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");

            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }

            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);

         }
      }});


      btn2.setContentAreaFilled( false );
      btn2.setBorder(null);
      btn2.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}
         if(cnt == 1) {
            start = "비전타워";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}
         if(cnt == 2) {
            destination = "비전타워";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance=dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");

            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }

            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);
         }
      } });


      btn3.setContentAreaFilled( false );
      btn3.setBorder(null);
      btn3.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}
         if(cnt == 1) {
            start = "법과대학";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}
         if(cnt == 2) {
            destination = "법과대학";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance =dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");

            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }

            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);
         }

      } });


      btn4.setContentAreaFilled( false );
      btn4.setBorder(null);
      btn4.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}
         if(cnt == 1) {
            start = "공과대학1";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}
         if(cnt == 2) {
            destination = "공과대학1";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance =dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");

            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }
            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);
         }
      } });

      btn5.setContentAreaFilled( false );
      btn5.setBorder(null);
      btn5.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}
         if(cnt == 1) {
            start = "공과대학2";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}
         if(cnt == 2) {
            destination = "공과대학2";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance=dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");

            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }
            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);
         }
      } });


      btn6.setContentAreaFilled( false );
      btn6.setBorder(null);
      btn6.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}
         if(cnt == 1) {
            start = "바이오나노대학";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}
         if(cnt == 2) {
            destination = "바이오나노대학";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance =dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");

            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }
            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);
         }
      } });


      btn7.setContentAreaFilled( false );
      btn7.setBorder(null);
      btn7.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}
         if(cnt == 1) {
            start = "IT대학";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}
         if(cnt == 2) {
            destination = "IT대학";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance =dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");

            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }
            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);
         }
      } });


      btn8.setContentAreaFilled( false );
      btn8.setBorder(null);
      btn8.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}
         if(cnt == 1) {
            start = "바이오나노연구원";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}
         if(cnt == 2) {
            destination = "바이오나노연구원";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance =dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");

            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }
            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);
         }
      } });


      btn9.setContentAreaFilled( false );
      btn9.setBorder(null);
      btn9.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}
         if(cnt == 1) {
            start = "예술대학2";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}
         if(cnt == 2) {
            destination = "예술대학2";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance =dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");

            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }
            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);
         }
      } });


      btn10.setContentAreaFilled( false );
      btn10.setBorder(null);
      btn10.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}
         if(cnt == 1) {
            start = "예술대학1";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}
         if(cnt == 2) {
            destination = "예술대학1";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance =dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");

            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }
            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);
         }
      } });


      btn11.setContentAreaFilled( false );
      btn11.setBorder(null);
      btn11.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}
         if(cnt == 1) {
            start = "글로벌센터";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}

         if(cnt == 2) {
            destination = "글로벌센터";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance =dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");

            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }
            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);
         }
      } });

      btn12.setContentAreaFilled( false );
      btn12.setBorder(null);
      btn12.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}

         if(cnt == 1) {
            start = "교육대학원";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}

         if(cnt == 2) {
            destination = "교육대학원";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance =dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");
            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }
            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);
         }
      } });


      btn13.setContentAreaFilled( false );
      btn13.setBorder(null);
      btn13.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}

         if(cnt == 1) {
            start = "중앙도서관";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}

         if(cnt == 2) {
            destination = "중앙도서관";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance =dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");

            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }
            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);

         }
      } });

      btn14.setContentAreaFilled( false );
      btn14.setBorder(null);
      btn14.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}

         if(cnt == 1) {
            start = "한의대";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}

         if(cnt == 2) {
            destination = "한의대";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance =dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");   
            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }

            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);
         }
      } });

      btn15.setContentAreaFilled( false );
      btn15.setBorder(null);
      btn15.addActionListener(new ActionListener(){@Override
         public void actionPerformed(ActionEvent e) {
         if(cnt == 0) {
            System.out.println(" 출발, 도착 버튼을 먼저 누르시길 바랍니다.");
            label.setText("Please press the departure and arrival buttons first.");}

         if(cnt == 1) {
            start = "기숙사";
            System.out.println(" 출발 건물 "+start+"을 선택하셨습니다.");
            label.setText("Start Point : "+ start);}

         if(cnt == 2) {
            destination = "기숙사";
            System.out.println(" 도착 건물 "+destination+"을 선택하셨습니다.");
            label.setText("Destination Point : "+ destination);

            DijkstraAlgorithm dijkstra =new DijkstraAlgorithm(start, destination);
            double distance=dijkstra.getDistance();
            label.setText(start+"에서 "+destination+"까지의 최소 이동 거리는 "+distance+"m"+" 입니다.");   

            stops =dijkstra.getStops();

            MyPath path = new MyPath(stops);

            int[] xpoints= new int[path.xArray.size()];
            for( int i=0; i<xpoints.length;i++)
            {
               xpoints[i]= path.xArray.get(i).intValue();
            }
            int[] ypoints= new int[path.yArray.size()];
            for( int i=0; i<ypoints.length;i++)
            {
               ypoints[i]= path.yArray.get(i).intValue();
            }
            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setStroke(new BasicStroke(3));

            g.setColor(Color.RED);
            g.drawPolyline(xpoints,ypoints,xpoints.length);

         }
      } });




      btn16.setContentAreaFilled( false ); //편의점
      btn16.setBorder(null);
      btn16.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) {
            System.out.println("편의점을 클릭하셨습니다.");
            label.setText("가천대학교내의 편의점의 위치는 다음과 같습니다...");
            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setColor(Color.RED);
            g.setStroke(new BasicStroke(3));
            g.drawOval(181,433,50,50);//it
            g.drawOval(330,500,50,50);//비타
            g.drawOval(432,318,50,50);//가천관
            g.drawOval(433,135,50,50);//중도
            g.drawOval(65,110,50,50); //기숙사
            //편의점있는 건물의 동그라미. ex IT대 앞, 법학대학, 가천관, 기숙사, 중앙도서관

         }
      });


      btn17.setContentAreaFilled( false ); //은행 
      btn17.setBorder(null);
      btn17.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) {
            System.out.println("은행을 클릭하셨습니다");
            label.setText("가천대학교내의 은행의 위치는 다음과 같습니다...");

            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setColor(Color.RED);
            g.setStroke(new BasicStroke(3));
            g.drawOval(381,498,50,50);

            //은행있는 건물의 동그라미. 법대

         }
      });

      btn18.setContentAreaFilled( false ); //보건실
      btn18.setBorder(null);
      btn18.addActionListener(new ActionListener() { 
         public void actionPerformed(ActionEvent e) {
            System.out.println("보건실을 클릭하셨습니다");
            label.setText("가천대학교내의 보건실의 위치는 다음과 같습니다...");
            Graphics2D g= (Graphics2D)CampusMapGUI.this.getGraphics();
            g.setColor(Color.RED);
            g.setStroke(new BasicStroke(3));
            g.drawOval(432,318,50,50);

            //보건실있는 건물의 동그라미. 가천관

         }
      });



      btn19.setContentAreaFilled( false );
      btn19.setBorder(null);
      btn19.addActionListener(new ActionListener() { //출발 버튼 눌렀을때 반응
         public void actionPerformed(ActionEvent e) {
            System.out.println("Click start building now...");
            label.setText("출발 지점의 건물을 선택하십시오...");
            //다음버튼은 출발로 입력되어야함
            cnt = 1;  //cnt ==1 일 때 start
         }
      });


      btn20.setContentAreaFilled( false );
      btn20.setBorder(null);
      btn20.addActionListener(new ActionListener() { //도착 버튼 눌렀을때 반응
         public void actionPerformed(ActionEvent e) {
            System.out.println("Click destination building now...");
            label.setText("도착 지점의 건물을 선택하십시오...");
            //다음버튼은 도착으로 입력되어야함  
            cnt = 2; //cnt ==2일 때 destination

         }
      });



      this.add(panel);
      this.setSize(800,620);
      this.setLocationRelativeTo(null);
      this.setVisible(true);

   }  //CampusMapGUI  끝괄호


   class MyPanel extends JPanel{

      public void paintComponent(Graphics g){
         super.paintComponent(g);
         g.drawImage(im,0,0,800,585,this);  //campus사진을 배경화면으로 넣음

      }

   }// class Mypanel extends JPanel 끝괄호




} //public class CampusMapGUI 끝괄호