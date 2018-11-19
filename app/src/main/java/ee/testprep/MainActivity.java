package ee.testprep;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.aspose.cells.Cell;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.LoadOptions;
import com.aspose.cells.Row;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Workbook workbook;
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //if there is no database already created, create one from .xlsx
        dbHelper = new DataBaseHelper(this);//TODO

        convertXLStoSQL();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_stats) {

        } else if (id == R.id.nav_learn) {

        } else if (id == R.id.nav_quiz) {

        } else if (id == R.id.nav_modeltest) {

        } else if (id == R.id.nav_contact) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void convertXLStoSQL() {

        Thread mThread = new Thread() {


            @Override
            public void run() {
                //File file = new File(getExternalFilesDir(null), "dummy.xlsx");
                File file = new File("/sdcard/dummy.xlsx");

                FileInputStream fstream;
                LoadOptions loadOptions = new LoadOptions(FileFormatType.XLSX);
                //loadOptions.setPassword("penke999");
                FileInputStream myInput = null;
                try {
                    fstream = new FileInputStream(file);
                    workbook = new Workbook(fstream, loadOptions);

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                if (workbook != null) {
                    Worksheet worksheet = workbook.getWorksheets().get(0);

                    Iterator<Row> rowIterator = worksheet.getCells().getRows().iterator();
                    rowIterator.hasNext();//skip header TODO

                    L.d(getLocalClassName(), "rows: " + worksheet.getCells().getRows().getCount());

                    while (rowIterator.hasNext()) {
                        int colIndex = 0;
                        Row row = rowIterator.next();
                        Iterator<Cell> cellIterator = row.iterator();
                        DBRow dbRow = new DBRow();

                        while (cellIterator.hasNext()) {
                            Cell cell = cellIterator.next();
                            switch (colIndex) {
                                case 0:
                                    dbRow.exam = cell.getDisplayStringValue();
                                    break;
                                case 1:
                                    dbRow.year = cell.getDisplayStringValue();
                                    break;
                                /*case 2:
                                    dbRow.qNo = cell.getIntValue()+1;
                                    L.d(getLocalClassName(), dbRow.qNo+"");
                                    break;*/
                                case 3:
                                    dbRow.question = cell.getDisplayStringValue();
                                    break;
                                case 4:
                                    dbRow.optionA = cell.getDisplayStringValue();
                                    break;
                                case 5:
                                    dbRow.optionB = cell.getDisplayStringValue();
                                    break;
                                case 6:
                                    dbRow.optionC = cell.getDisplayStringValue();
                                    break;
                                case 7:
                                    dbRow.optionD = cell.getDisplayStringValue();
                                    break;
                                case 8:
                                    dbRow.answer = cell.getDisplayStringValue();
                                    break;
                                case 9:
                                    dbRow.ipc = cell.getDisplayStringValue();
                                    break;
                                case 10:
                                    dbRow.subject = cell.getDisplayStringValue();
                                    break;
                                default:
                                    break;
                            }

                            colIndex++;
                        }

                        dbHelper.insertRow(dbRow);
                    }
                }
            }
        };
        mThread.start();

    }
}
