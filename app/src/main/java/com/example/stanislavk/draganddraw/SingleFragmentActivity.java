package com.example.stanislavk.draganddraw;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by LasVegas on 06.07.2017.
 */

public abstract class SingleFragmentActivity extends FragmentActivity {

    /*В этом коде представление активности заполняется по данным activity_fragment.xml.
    Затем мы ищем фрагмент в FragmentManager этого контейнера, создавая и добавляя его, если он не существует.
    Абстрактный методо createFragment(), используется для создания экземпляра фрагмента.
    Субклассы SingleFragmentActivity реализуют этот метод так, чтобы он возвращал экземпляр фрагмента,
    хостом которого является активность.
    *
    * */

    /*Поскольку в файле activity_crime.xml не указан конкретный фрагмент,
    он может использоваться для
    любой активности, выполняющей функции хоста для одного фрагмента.*/

    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
