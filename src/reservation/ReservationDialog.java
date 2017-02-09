package reservation;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ReservationDialog extends Dialog implements ActionListener, WindowListener,ItemListener{

	boolean canceled;
	Panel panelNorth;
	Panel panelMid;
	Panel panelSouth;
	ChoiceFacility choiceFacility;
	TextField tfYear,tfMonth,tfDay;
	ChoiceHour startHour;
	ChoiceMinute startMinute;
	ChoiceHour endHour;
	ChoiceMinute endMinute;
	Button buttonOK;
	Button buttonCancel;
	
	
	
	public ReservationDialog(Frame owner) {
		super(owner,"新規予約", true);
		// TODO Auto-generated constructor stub
		//キャンセルは初期値ではtrueとしておく
		        canceled = true;
		        //施設選択ボックスの生成
		        choiceFacility = new ChoiceFacility();
		        //テキストフィールドの生成　年月日
		        tfYear = new TextField("",4);
		        tfMonth = new TextField("",2);
		        tfDay = new TextField("",2);
		        //開始時刻　時分選択ボックスの生成
		        startHour = new ChoiceHour();
		        startMinute = new ChoiceMinute();
		        //終了時刻　自分選択ボックスの生成
		        endHour = new ChoiceHour();
		        endMinute = new ChoiceMinute();
		        //ボタンの生成
		        buttonOK = new Button("予約実行");
		        buttonCancel = new Button("キャンセル");
		        //パネルの生成
		        panelNorth = new Panel();
		        panelMid = new Panel();
		        panelSouth = new Panel();
		        //上部パネルに施設選択ボックス，年月日入力欄を追加
		        panelNorth.add( new Label("施設　"));
		        panelNorth.add(choiceFacility);
		        panelNorth.add( new Label("予約日 "));
		        panelNorth.add(tfYear);
		        panelNorth.add(new Label("年"));
		        panelNorth.add(tfMonth);
		        panelNorth.add(new Label("月"));
		        panelNorth.add(tfDay);
		        panelNorth.add(new Label("日　"));
		        //中央パネルに予約　開始時刻，終了時刻入力用選択ボックスを追加
		        panelMid.add( new Label("予約時間　"));
		        panelMid.add( startHour);
		        panelMid.add( new Label("時"));
		        panelMid.add( startMinute);
		        panelMid.add( new Label("分　〜　"));
		        panelMid.add( endHour);
		        panelMid.add( new Label("時"));
		        panelMid.add( endMinute);
		        panelMid.add( new Label("分"));
		        //下部パネルに2つのボタンを追加
		        panelSouth.add(buttonCancel);
		        panelSouth.add( new Label("　　　　"));
		        panelSouth.add(buttonOK);
		        // ReservationDialogをBorderLayoutに設定し，3つのパネルを追加
		        setLayout(new BorderLayout());
		        add(panelNorth, BorderLayout.NORTH);
		        add(panelMid,BorderLayout.CENTER);
		        add(panelSouth, BorderLayout.SOUTH);
		        // ウィンドウリスナを追加
		        addWindowListener(this);
		        // ボタンにアクションリスナを追加
		        buttonOK.addActionListener(this);
		        buttonCancel.addActionListener(this);
		        //施設選択ボックス，時・分選択ボックスそれぞれに項目リスナを追加
		        choiceFacility.addItemListener(this);
		        startHour.addItemListener(this);
		        startMinute.addItemListener(this);
		        endHour.addItemListener(this);
		        endMinute.addItemListener(this);
		        // 選択されている施設によって，時刻の範囲を設定する．
		        resetTimeRange();
		        // 大きさの設定，ウィンドウのサイズ変更不可の設定
		        this.setBounds( 100, 100, 500, 120);
		        setResizable(false);
		    }
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==choiceFacility){
			resetTimeRange();
		}else if(e.getSource()==startHour){
			int start = Integer.parseInt(startHour.getSelectedItem());
			endHour.resetRange(start, Integer.parseInt(endHour.getLast()));
		}else if (e.getSource()==endHour){
			if(endHour.getSelectedIndex()==endHour.getItemCount()-1){
				endMinute.select(0);
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		setVisible(false);
		dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        if ( e.getSource() == buttonCancel){
            setVisible(false);
            dispose();
        } else if ( e.getSource() == buttonOK){
            canceled = false;
            setVisible(false);
            dispose();
        }
	}
	

    private void resetTimeRange() {
        // 選択されている施設によって，時刻の範囲を設定する．
        if ( choiceFacility.getSelectedIndex()==0){
            // 最初の施設(小ホールのとき)の設定
            startHour.resetRange(10, 20);
            endHour.resetRange(10, 21);
        } else {
            // 小ホール以外の設定
            startHour.resetRange(9, 19);
            endHour.resetRange(9, 20);
        }
    }

}
