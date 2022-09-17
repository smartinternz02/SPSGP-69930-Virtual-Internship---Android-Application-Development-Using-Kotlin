package ghanam.com.gsapp.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ghanam.com.gsapp.databinding.FragmentHomeBinding
import ghanam.com.gsapp.databinding.ItemCardBinding
import ghanam.com.gsapp.dataclasses.Item
import ghanam.com.gsapp.dataclasses.products.Product
import ghanam.com.gsapp.singletons.Capitalizer
import ghanam.com.gsapp.singletons.DecimalFormatter
import ghanam.com.gsapp.singletons.VirtualDB

class HomeItemAdapter(  private val items: MutableList<Product>, private val homeBinding: FragmentHomeBinding) : RecyclerView.Adapter<HomeItemAdapter.TaskViewHolder>() {
    class TaskViewHolder(val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root)
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        context=parent.context
        return TaskViewHolder(
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }


    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentItem = items[position]
        holder.binding.apply {
            priceText.text=DecimalFormatter.format(currentItem.price)
            itemNameText.text=Capitalizer.capitalized(currentItem.name)
            itemDescriptionText.text=Capitalizer.capitalized(currentItem.description)
            skuText.text=currentItem.sku
            val id=context.resources.getIdentifier(currentItem.image,"drawable",context.packageName)
            itemImageView.setImageResource(id)
            addButton.setOnClickListener {
                if (!VirtualDB.addItem(Item(currentItem.name,currentItem.price,currentItem.image,1))){
                    Toast.makeText(context,"you added item already, you can modify quantity in cart",Toast.LENGTH_SHORT).show()
                }
                homeBinding.itemsCountText.text=VirtualDB.getItemsCount().toString()
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}