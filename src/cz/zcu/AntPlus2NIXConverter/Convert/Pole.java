
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Pole extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pole);
        TextView pole = (TextView) findViewById(R.id.textView5);

        Intent i = getIntent();
        ArrayList predat = prepis(i.getIntegerArrayListExtra("text"));
        pole.setText(predat.toString());
    }

    public ArrayList<Integer> prepis(ArrayList<Integer> list){
        ArrayList<Integer> nove = new ArrayList<Integer>();
        nove.add(list.get(0));
        for(int i = 1; i < list.size(); i++){
            if(list.get(i-1) != list.get(i)){
                nove.add(list.get(i));
            }
        }
        return nove;
    }
}
