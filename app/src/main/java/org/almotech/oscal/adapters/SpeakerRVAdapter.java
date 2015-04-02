package org.almotech.oscal.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.almotech.oscal.R;
import org.almotech.oscal.activities.PersonDetailActivity;
import org.almotech.oscal.model.SpeakerModel;
import org.almotech.oscal.utils.LoadImageUtil;

import java.util.ArrayList;

/**
 * Created by Armando Shkurti on 2015-03-23.
 */
public class SpeakerRVAdapter extends RecyclerView.Adapter<SpeakerRVAdapter.SpeakerVH> {

    private static ArrayList<SpeakerModel> modelArrayList;
    private LoadImageUtil mLoadImageUtil;

    public SpeakerRVAdapter(ArrayList<SpeakerModel> modelArrayList) {

        this.modelArrayList=modelArrayList;
    }

    @Override
    public SpeakerVH onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.speaker_item,viewGroup,false);

        SpeakerVH mSpeakerVH = new SpeakerVH(mView);
        return mSpeakerVH;
    }

    @Override
    public void onBindViewHolder(SpeakerVH speakerVH, int position) {

        if(mLoadImageUtil == null) mLoadImageUtil = new LoadImageUtil(speakerVH.speakerIamge.getContext());
        mLoadImageUtil.loadBitmapToImageView(speakerVH.speakerIamge,modelArrayList.get(position).getImage_url());
        speakerVH.speakerName.setText(Html.fromHtml(modelArrayList.get(position).getName()));
        speakerVH.speakerCompany.setText(modelArrayList.get(position).getCompany());
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public static class SpeakerVH extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView speakerName;
        public TextView speakerCompany;
        public ImageView speakerIamge;

        public SpeakerVH(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            speakerName = (TextView) itemView.findViewById(R.id.speakerName);
            speakerCompany = (TextView) itemView.findViewById(R.id.speakerCompany);
            speakerIamge = (ImageView) itemView.findViewById(R.id.speakerImg);

        }

        @Override
        public void onClick(View v) {
            Intent mIntent = new Intent(v.getContext(), PersonDetailActivity.class);
            mIntent.putExtra("name",modelArrayList.get(getAdapterPosition()).getName());
            mIntent.putExtra("url",modelArrayList.get(getAdapterPosition()).getImage_url());
            mIntent.putExtra("company",modelArrayList.get(getAdapterPosition()).getCompany());
            mIntent.putExtra("descr",modelArrayList.get(getAdapterPosition()).getDescription());
            v.getContext().startActivity(mIntent);
        }
    }
}
