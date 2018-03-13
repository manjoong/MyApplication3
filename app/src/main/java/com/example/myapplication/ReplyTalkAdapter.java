package com.example.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 마루소프트 on 2018-02-01.
 */


public class ReplyTalkAdapter extends RecyclerView.Adapter<ReplyTalkAdapter.ViewHolder> {
    Context context;
    ArrayList<Reply_CallBackItem.Data> items;

    //커뮤니티 토크 어댑터 함수를 만듬으로써 context와 item을 상속해 준다.
    public ReplyTalkAdapter(Context context, ArrayList<Reply_CallBackItem.Data> items) {
        this.context = context;
        this.items = items;
    }

    //in create view holder는 뷰홀더에 넣어줄 layout을 찾는 애다.
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_reply_list,parent,false);
        return new ViewHolder(view);
    }
    //온바인드뷰홀더는 아이템을 세팅하거나 스크롤링 할때 호출되는 애다. 때문에 position이 필요하다.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        holder.no.setText(items.get(position).getR_no());
//        holder.t_no.setText(items.get(position).getR_t_no());
        holder.id.setText(items.get(position).getR_user_id());
        holder.content.setText(items.get(position).getR_content());
        holder.date.setText(items.get(position).getR_write_date());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Reply_CallBackItem.Data data) {
        items.add(0, data);
        notifyDataSetChanged();
    }

    //뷰홀더라는 애는 아이템안에 들어갈 텍스트등의 내용을 초기화 하는 역할이다.
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView content, id, date, no, t_no, like;
        public ViewHolder(View itemView) {
            super(itemView);
//            itemView.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View v) {
//                    System.out.println(getPosition());
//                    //어댑터에서는 this를 쓸 수 없으므로 context를 쓴다. context는 이 레이아웃의 변수들?
//                    Intent intent = new Intent(v.getContext() , DetailActivity.class);
//                    //변수를 해당 activity로 넘긴다.
//                    intent.putExtra("no", items.get(getAdapterPosition()).getR_no());
//                    intent.putExtra("t_no", items.get(getAdapterPosition()).getR_t_no());
//                    intent.putExtra("id", items.get(getAdapterPosition()).getR_user_id());
//                    intent.putExtra("date", items.get(getAdapterPosition()).getR_write_date());
//                    intent.putExtra("content", items.get(getAdapterPosition()).getR_content());
//                    v.getContext().startActivity(intent.addFlags(FLAG_ACTIVITY_NEW_TASK));
//                }
//            });
            content = itemView.findViewById(R.id.tv_text_content);
            id = itemView.findViewById(R.id.tv_text_id);
            like = itemView.findViewById(R.id.tv_text_like);
            date = itemView.findViewById(R.id.tv_text_date);
        }
    }
}