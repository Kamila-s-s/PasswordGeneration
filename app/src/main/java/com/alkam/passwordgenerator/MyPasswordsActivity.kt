package com.alkam.passwordgenerator

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alkam.passwordgenerator.database.PasswordDatabase
import com.alkam.passwordgenerator.models.PasswordModel
import com.alkam.passwordgenerator.viewmodels.PasswordViewModel
import com.alkam.passwordgenerator.viewmodels.PasswordViewModelFactory

import kotlinx.android.synthetic.main.activity_my_passwords.*

class MyPasswordsActivity : AppCompatActivity() {
    private lateinit var viewModel: PasswordViewModel

    private val adapter =
        PasswordsAdapter(callback = { index: Int ->
            viewModel.deletePassword(index)
            passwords_rw.removeViewAt(index)
        })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_passwords)
        setSupportActionBar(findViewById(R.id.my_passwords_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val application = requireNotNull(this).application
        val dao = PasswordDatabase.getInstance(application).getDao()
        val viewModelFactory = PasswordViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(PasswordViewModel::class.java)

        passwords_rw.adapter = adapter

        viewModel.password.observe(this, Observer { passwords ->
            adapter.updateData(passwords)
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

class PasswordsAdapter(
    var items: List<PasswordModel> = mutableListOf(),
    val callback: (index: Int) -> Unit
) :
    RecyclerView.Adapter<PasswordsAdapter.PasswordsHolder>() {

    fun updateData(list: List<PasswordModel>) {
        items = list
        notifyDataSetChanged()
    }

    inner class PasswordsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.item_name)
        private val url = itemView.findViewById<TextView>(R.id.item_url)
        private val login = itemView.findViewById<TextView>(R.id.item_login)
        private val password = itemView.findViewById<TextView>(R.id.item_password)
        private val note = itemView.findViewById<TextView>(R.id.item_note)
        private val button = itemView.findViewById<ImageButton>(R.id.item_delete_btn)

        fun bind(item: PasswordModel) {
            name.text = item.name
            url.text = item.URL
            login.text = item.login
            password.text = item.password
            note.text = item.note
            button.setOnClickListener {
                callback(adapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordsHolder =
        PasswordsHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.password_item,
                parent,
                false
            )
        )


    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: PasswordsHolder, position: Int) {
        holder.bind(items[position])
    }

}
