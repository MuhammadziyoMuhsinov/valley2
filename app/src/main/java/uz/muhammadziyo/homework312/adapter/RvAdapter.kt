package uz.muhammadziyo.homework312.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import uz.muhammadziyo.homework312.databinding.ItemRvBinding
import uz.muhammadziyo.homework312.models.User

class RvAdapter(var list: List<User>, val rvClick: RvClick) :
    RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(var itemRv: ItemRvBinding) : RecyclerView.ViewHolder(itemRv.root) {

        fun onBind(user: User) {
            itemRv.ccy.text=user.Ccy
            itemRv.ccynmen.text = user.CcyNm_EN

            itemRv.root.setOnClickListener {
                rvClick.onClick(user)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size


}

interface RvClick {
    fun onClick(user: User)
}