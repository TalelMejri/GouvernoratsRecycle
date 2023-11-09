package com.example.gouvernoratsrecycle

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(),CustomerAdapter.OnItemClickListener  {

    var infoArray = arrayListOf(
        InfoGovernorates("elkef", R.drawable.elkef),
        InfoGovernorates("medenine",  R.drawable.medenine),
        InfoGovernorates("kairouan",  R.drawable.kairouan),
        InfoGovernorates("kasserine", R.drawable.kasserine),
        InfoGovernorates("kebili",  R.drawable.kebili),
        InfoGovernorates("manouba",  R.drawable.manouba),
        InfoGovernorates("mahdia", R.drawable.mahdia),
        InfoGovernorates("monastir",  R.drawable.monastir),
        InfoGovernorates("nabeul",  R.drawable.nabeul),
        InfoGovernorates("sfax", R.drawable.sfax),
        InfoGovernorates("sidi bouzid",  R.drawable.sisibouzid),
        InfoGovernorates("siliana",  R.drawable.siliana),
        InfoGovernorates("sousse", R.drawable.sousse),
        InfoGovernorates("tataouine",  R.drawable.tataouine),
        InfoGovernorates("tozeur",  R.drawable.tozeur),
        InfoGovernorates("tunis",  R.drawable.tunis),
        InfoGovernorates("zaghouan", R.drawable.zaghouan),
        InfoGovernorates("ariana",  R.drawable.ariana),
        InfoGovernorates("ben_arous",  R.drawable.ben_arous),
        InfoGovernorates("bizerte",  R.drawable.bizerte),
        InfoGovernorates("gabes", R.drawable.gabes),
        InfoGovernorates("gafsa",  R.drawable.gafsa)
    )

    private val originalInfoArray = ArrayList(infoArray)

    private lateinit var recyclerView: RecyclerView
    private lateinit var manager:RecyclerView.LayoutManager
    private lateinit var myAdapter:RecyclerView.Adapter<*>
    private lateinit var floatingActionButton:FloatingActionButton
    private lateinit var search:EditText
    private lateinit var info:FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager=LinearLayoutManager(this);
        myAdapter=CustomerAdapter(infoArray,this);
        floatingActionButton=findViewById(R.id.floatingActionButton)
        search=findViewById(R.id.search)
        info=findViewById(R.id.info)
        recyclerView=findViewById<RecyclerView?>(R.id.recycleView).apply {
            layoutManager=manager;
            adapter=myAdapter
        }

        search.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                FilterByName(s.toString());
            }
            override fun afterTextChanged(s: Editable?) {
                FilterByName(s.toString());
            }
        })


        /*
        recycler = findViewById(R.id.recycleView)
        recycler.layoutManager=layoutRecycler
        recycler.adapter = adapter*/
   }

    private fun FilterByName(name: String) {
        if (name.isEmpty()) {
            infoArray.clear()
            infoArray.addAll(originalInfoArray)
            myAdapter.notifyDataSetChanged()
        } else {
            val filteredArray = originalInfoArray.filter { it.name.toLowerCase().contains(name.toLowerCase()) }
            if(filteredArray.isNotEmpty()){
                infoArray.clear()
                infoArray.addAll(filteredArray)
                myAdapter.notifyDataSetChanged()
            }else{
                infoArray.clear()
                infoArray.addAll(filteredArray)
                myAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Not Found",
                    Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun DeleteGover(index: Int){
        infoArray.remove(infoArray.get(index))
        originalInfoArray.remove(originalInfoArray.get(index))
        myAdapter.notifyItemRemoved(index)
        myAdapter.notifyItemRangeChanged(index, infoArray.size)
        myAdapter.notifyDataSetChanged()
        Toast.makeText(this, "Governorate Deleted With Success",
            Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(position: Int) {

        //Delete Item
        floatingActionButton.setOnClickListener {
            val confirm:AlertDialog.Builder
            confirm=AlertDialog.Builder(this);
            confirm.setMessage("Do You Wanna Delete ${infoArray[position].name}")
            confirm.setTitle("Confirmation")
            confirm.setPositiveButton(
                "Confirm"
            ) { dialogInterface, i ->  DeleteGover(position) }

            confirm.setNegativeButton("close",
                { dialogInterface, i -> Int })
            val a = confirm.create()
            a.show()
        }

        //Show Details
        info.setOnClickListener {
            var intent = Intent(this,DetailsGover::class.java)
            intent.putExtra("governorate_name", infoArray[position].name)
             intent.putExtra("governorate_photo", infoArray[position].imageId)
            startActivity(intent)
        }
    }

}