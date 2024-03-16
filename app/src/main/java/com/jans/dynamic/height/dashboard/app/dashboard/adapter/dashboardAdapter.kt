package com.jans.dynamic.height.dashboard.app.dashboard.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.android_project_staggered.dashboard.Util.config.Companion.ITEM_R1
import com.example.android_project_staggered.dashboard.Util.config.Companion.ITEM_R2
import com.example.android_project_staggered.dashboard.Util.config.Companion.ITEM_R3
import com.example.android_project_staggered.dashboard.Util.config.Companion.ITEM_RT
import com.example.android_project_staggered.dashboard.Util.config.Companion.ITEM_RTF
import com.example.android_project_staggered.dashboard.Util.config.Companion.ITEM_RTH
import com.example.android_project_staggered.dashboard.Util.config.Companion.ITEM_S1
import com.example.android_project_staggered.dashboard.Util.config.Companion.ITEM_S2
import com.example.android_project_staggered.dashboard.Util.config.Companion.tvWidth
import com.example.android_project_staggered.dashboard.enums.blockDashboard
import com.example.android_project_staggered.dashboard.modelDashb.DashboardItem
import com.jans.dynamic.height.dashboard.app.R


class dashboardAdapter(private var dashboardItem: List<DashboardItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var isExpanded = false
    lateinit var context: Context
    override fun getItemCount(): Int {
        return dashboardItem.size
    }

    private fun getViewTypeLayout(resourceName: Int, parent: ViewGroup): View {
        return LayoutInflater.from(parent.context).inflate(resourceName, parent, false)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_R1 -> R1ViewHolder(getViewTypeLayout(R.layout.item_r1, parent))
            ITEM_R2 -> R2ViewHolder(getViewTypeLayout(R.layout.item_r2, parent))
            ITEM_R3 -> R3ViewHolder(getViewTypeLayout(R.layout.item_r3, parent))
            ITEM_S1 -> S1ViewHolder(getViewTypeLayout(R.layout.item_s1, parent))
            ITEM_S2 -> S2ViewHolder(getViewTypeLayout(R.layout.item_s2, parent))
            ITEM_RTF -> RTFViewHolder(getViewTypeLayout(R.layout.item_rtf, parent))
            ITEM_RTH -> RTHViewHolder(getViewTypeLayout(R.layout.item_rth, parent))
            ITEM_RT -> RTViewHolder(getViewTypeLayout(R.layout.item_rt, parent))
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }
    @SuppressLint("DiscouragedApi")
    private fun getDrawableResourceId(context: Context, imageNameFromJson: String): Int {
        val packageName = context.packageName
        val res = context.resources.getIdentifier(imageNameFromJson, "drawable", packageName)

        if (imageNameFromJson == "" || imageNameFromJson.isEmpty()){
            return context.resources.getIdentifier("ic_launcher_background", "drawable", packageName)
        }
        else if (res == 0){
            return context.resources.getIdentifier("ic_launcher_background", "drawable", packageName)
        }
        else{
            return res
        }



    }
    override fun getItemViewType(position: Int): Int {
        return when (dashboardItem[position].block) {
            blockDashboard.R1.toString() -> ITEM_R1
            blockDashboard.R2.toString() -> ITEM_R2
            blockDashboard.R3.toString() -> ITEM_R3
            blockDashboard.S1.toString() -> ITEM_S1
            blockDashboard.S2.toString() -> ITEM_S2
            blockDashboard.RTF.toString() -> ITEM_RTF
            blockDashboard.RTH.toString() -> ITEM_RTH
            blockDashboard.RT.toString() -> ITEM_RT
            else -> ITEM_S1
        }
    }

    fun bitmapDrawable1(backgroundImage: Bitmap, maxHeight: Int): BitmapDrawable {
        return BitmapDrawable(
            context.resources,
            Bitmap.createScaledBitmap(backgroundImage, 400, maxHeight, true)
        )
    }

    private fun setupSublistRV(h: R3ViewHolder) {
        val listSubList = listOf(
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC.",
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc."
        )
        h.rvSubList.layoutManager = LinearLayoutManager(h.itemView.context)
        val adapter = SubListAdapter(listSubList)
        h.rvSubList.adapter = adapter
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        context = holder.itemView.context
        val dashboardItems = dashboardItem[pos]

        when (getItemViewType(pos)) {
            ITEM_R1 -> itemR1Code(holder as R1ViewHolder,dashboardItems)
            ITEM_R2 -> itemR2Code(holder as R2ViewHolder,dashboardItems)
            ITEM_R3 -> itemR3Code(holder as R3ViewHolder,dashboardItems,pos)
            ITEM_S1 -> itemS1Code(holder as S1ViewHolder,dashboardItems)
            ITEM_S2 -> itemS2Code(holder as S2ViewHolder,dashboardItems)
            ITEM_RT -> itemRTCode(holder as RTViewHolder,dashboardItems)
            ITEM_RTF -> itemRTFCode(holder as RTFViewHolder,dashboardItems)
            ITEM_RTH -> itemRTHCode(holder as RTHViewHolder,dashboardItems)
            else -> {
            }
        }

    }



    /*
            R1 Code
     */
    private fun itemR1Code(holder: R1ViewHolder, dashboardItems:DashboardItem){
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val maxHeight = (tvWidth) + 0
            val backgroundImage: Bitmap = BitmapFactory.decodeResource(
                context.resources,
                getDrawableResourceId(context, dashboardItems.imageName)
            )
            val layerDrawable =
                LayerDrawable(arrayOf(bitmapDrawable1(backgroundImage, maxHeight)))
            holder.imgBG.background = (layerDrawable)
            val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
            holder.itemView.layoutParams.height = maxHeight
//            mainL.setBackgroundColor(context.resources.getColor(R.color.green))
        }, 50)
    }
    /*
            R2 Code
     */
    private fun itemR2Code(holder: R2ViewHolder, dashboardItems:DashboardItem){
        val paramStaggered =
            (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams)
        paramStaggered.isFullSpan = true
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title


        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val maxHeight = (tvWidth) / 2
            val backgroundImage: Bitmap = BitmapFactory.decodeResource(
                context.resources,
                getDrawableResourceId(context, dashboardItems.imageName)
            )
            holder.itemView.layoutParams.height = maxHeight

            val layerDrawable =
                LayerDrawable(arrayOf(bitmapDrawable1(backgroundImage, maxHeight)))
            holder.imgBG.background = (layerDrawable)
            val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
//            mainL.setBackgroundColor(context.resources.getColor(R.color.green))
        }, 50)
    }
    /*
            R3 Code
     */
    private fun itemR3Code(holder: R3ViewHolder, dashboardItems:DashboardItem, pos:Int){
        val tv1 = holder.tv1
        val paramStaggered =
            (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams)
        paramStaggered.isFullSpan = true
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        tv1.text = title
        tv1.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                tv1.viewTreeObserver.removeOnPreDrawListener(this)
                tvWidth = tv1.width
                return true
            }
        })
        val layMain = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)

        android.os.Handler(Looper.getMainLooper()).postDelayed({

            holder.dropDownbtn.visibility = VISIBLE
            val urlR3 =
                "https://images.pexels.com/photos/326055/pexels-photo-326055.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2";
            paramStaggered.isFullSpan = true
            layMain.layoutParams.height = 504
            tv1.setPadding(0, 80, 0, 80)
            tv1.setTextColor(Color.WHITE)
            setupSublistRV(holder)

            Glide.with(holder.itemView.context)
                .asBitmap()
                .load(urlR3).placeholder(R.drawable.loading_spinner)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {

                        val maxHeight = 504
                        val layerDrawable =
                            LayerDrawable(arrayOf(bitmapDrawable1(resource, maxHeight)))
                        holder.imgBG.background = layerDrawable
                        val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
//                        mainL.setBackgroundColor(context.resources.getColor(R.color.green))
                    }
                })


            holder.itemView.setOnClickListener {
                isExpanded = !isExpanded
                if (isExpanded) {
                    holder.rvSubList.visibility = VISIBLE
                    layMain.setBackgroundColor(Color.TRANSPARENT)
                    holder.dropDownbtn.setImageResource(R.drawable.top_arrow_svgrepo_com)
                } else {
                    holder.rvSubList.visibility = GONE
                    layMain.setBackgroundColor(Color.TRANSPARENT)
                    holder.dropDownbtn.setImageResource(R.drawable.bottom_arrow_svgrepo_com)
                    notifyItemChanged(pos)
                }
            }
        }, 50)
    }
    /*
                RT Code
         */
    private fun itemRTCode(holder: RTViewHolder, dashboardItems:DashboardItem){

        val paramStaggered =
            (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams)
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title
        paramStaggered.isFullSpan = true

        val cardBG = holder.itemView.findViewById<CardView>(R.id.cardV)
        cardBG.setCardBackgroundColor(Color.BLACK)
        val linParent = holder.itemView.findViewById<LinearLayout>(R.id.linParent)
        linParent.layoutParams.height = 504

    }
    /*
            RTF Code
     */
    private fun itemRTFCode(holder: RTFViewHolder, dashboardItems:DashboardItem){
        Log.d("type", ITEM_RTF.toString())
        val paramStaggered =
            (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams)
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title
        paramStaggered.isFullSpan = true
        holder.tv1.text = title
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            paramStaggered.isFullSpan = true
            holder.tv1.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            holder.tv1.setPadding(0, 80, 0, 80)
            val maxHeight = tvWidth / 2 + 0
            val backgroundImage: Bitmap = BitmapFactory.decodeResource(
                context.resources,
                getDrawableResourceId(context, dashboardItems.imageName)
            )
            val layerDrawable =
                LayerDrawable(arrayOf(bitmapDrawable1(backgroundImage, maxHeight)))
            holder.imgBG.background = layerDrawable
            holder.tv1.setTextColor(Color.BLACK)
            val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
//            mainL.setBackgroundColor(context.resources.getColor(R.color.green))
        }, 50)
    }
    /*
            RTH Code
     */
    private fun itemRTHCode(holder: RTHViewHolder, dashboardItems:DashboardItem){
        val paramStaggered =
            (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams)
        paramStaggered.isFullSpan = true
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title
        paramStaggered.isFullSpan = true
        holder.tv1.text = title
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            paramStaggered.isFullSpan = true
            holder.tv1.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            holder.tv1.setPadding(0, 80, 0, 80)
            val maxHeight = tvWidth+ tvWidth+126
            val backgroundImage: Bitmap = BitmapFactory.decodeResource(
                context.resources,
                getDrawableResourceId(context, dashboardItems.imageName)
            )
            val layerDrawable =
                LayerDrawable(arrayOf(bitmapDrawable1(backgroundImage, maxHeight)))
            holder.imgBG.background = layerDrawable
            holder.tv1.setTextColor(Color.BLACK)
            val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
//            mainL.setBackgroundColor(context.resources.getColor(R.color.green))


        }, 50)
    }
    /*
            S1 Code
     */
    private fun itemS1Code(holder: S1ViewHolder, dashboardItems:DashboardItem){
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val maxHeight = (tvWidth) / 2
            holder.itemView.layoutParams.height = maxHeight

            val backgroundImage: Bitmap = BitmapFactory.decodeResource(
                context.resources,
                getDrawableResourceId(context, dashboardItems.imageName)
            )
            val layerDrawable =
                LayerDrawable(arrayOf(bitmapDrawable1(backgroundImage, maxHeight)))
            holder.imgBG.background = (layerDrawable)
            val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
//            mainL.setBackgroundColor(context.resources.getColor(R.color.green))
        }, 50)
    }
    /*
            S2 Code
     */
    private fun itemS2Code(holder: S2ViewHolder, dashboardItems:DashboardItem){
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val maxHeight = (tvWidth) / 2
            holder.itemView.layoutParams.height = maxHeight

            val backgroundImage: Bitmap = BitmapFactory.decodeResource(
                context.resources,
                getDrawableResourceId(context, dashboardItems.imageName)
            )
            val layerDrawable =
                LayerDrawable(arrayOf(bitmapDrawable1(backgroundImage, maxHeight)))
            holder.imgBG.background = (layerDrawable)
            val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
//            mainL.setBackgroundColor(context.resources.getColor(R.color.green))
                    }, 50)
    }

    // View Holders
    class R1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv1 = itemView.findViewById<TextView>(R.id.text1)
        var imgBG = itemView.findViewById<ImageView>(R.id.imgBackground)

    }

    inner class R2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv1 = itemView.findViewById<TextView>(R.id.text1)
        var imgBG = itemView.findViewById<ImageView>(R.id.imgBackground)
    }

    inner class R3ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv1 = itemView.findViewById<TextView>(R.id.text1)
        var imgBG = itemView.findViewById<ImageView>(R.id.imgBackground)
        var rvSubList = itemView.findViewById<RecyclerView>(R.id.rvAdapterSubList)
        var dropDownbtn = itemView.findViewById<ImageView>(R.id.id1)
    }

    inner class S1ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv1 = itemView.findViewById<TextView>(R.id.text1)
        var imgBG = itemView.findViewById<ImageView>(R.id.imgBackground)
    }

    inner class S2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv1 = itemView.findViewById<TextView>(R.id.text1)
        var imgBG = itemView.findViewById<ImageView>(R.id.imgBackground)
    }

    inner class RTFViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv1 = itemView.findViewById<TextView>(R.id.text1)
        var imgBG = itemView.findViewById<ImageView>(R.id.imgBackground)
    }

    inner class RTViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv1 = itemView.findViewById<TextView>(R.id.text1)
        var imgBG = itemView.findViewById<ImageView>(R.id.imgBackground)
    }

    inner class RTHViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv1 = itemView.findViewById<TextView>(R.id.text1)
        var imgBG = itemView.findViewById<ImageView>(R.id.imgBackground)
    }

    inner class SubListAdapter(private val childList: List<String>) : RecyclerView.Adapter<SubListAdapter.subListViewHolder>() {

        inner class subListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.child_Title)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): subListViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.child_row, parent, false)
            return subListViewHolder(view)
        }

        override fun onBindViewHolder(holder: subListViewHolder, position: Int) {
            holder.title.text = childList[position]
        }

        override fun getItemCount(): Int {
            return childList.size
        }
    }


}



