package com.example.com.record07150919.mFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.com.record07150919.R;
import com.example.com.record07150919.Record;
import com.example.com.record07150919.RecordActivity;
import com.example.com.record07150919.RecordLab;
import com.example.com.record07150919.RecordPagerActivity;

import java.util.List;

/**
 * Created by 10449 on 2017/3/8.
 */

public class RecordListFrament extends Fragment {
    private RecyclerView mRecordRecylerView;
    private RecordAdapter mAdapter;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.fragment_record_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_new_record:
                Record record = new Record();
                RecordLab.get(getActivity()).addRecord(record);
                Intent intent = RecordPagerActivity.newIntent(getActivity(),record.getmId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v("aaabbb", "kaishi2");
        View view = inflater.inflate(R.layout.frament_record_lilist, container, false);
        mRecordRecylerView = (RecyclerView) view.findViewById(R.id.record_recyler_view);
        mRecordRecylerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private void updateUI() {
        RecordLab recordLab = RecordLab.get(getActivity());
        List<Record> records = recordLab.getRecords();
        if (mAdapter == null) {
            mAdapter = new RecordAdapter(records);
            mRecordRecylerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        mAdapter = new RecordAdapter(records);
        mRecordRecylerView.setAdapter(mAdapter);
    }

    private class RecordHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;
        private Record mRecord;

        public RecordHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_record_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_record_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_record_solved_check_box);
        }

        public void bindRecord(Record record) {
            mRecord = record;
            mTitleTextView.setText(mRecord.getmTitle());
            mDateTextView.setText(mRecord.getmDate().toString());
            mSolvedCheckBox.setChecked(mRecord.ismSolved());
        }

        @Override
        public void onClick(View v) {
            Intent intent = RecordPagerActivity.newIntent(getActivity(), mRecord.getmId());
            startActivity(intent);
        }
    }

    private class RecordAdapter extends RecyclerView.Adapter<RecordHolder> {

        private List<Record> mRecords;

        public RecordAdapter(List<Record> records) {
            mRecords = records;
        }

        @Override
        public RecordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_record, parent, false);
            return new RecordHolder(view);
        }

        @Override
        public void onBindViewHolder(RecordHolder holder, int position) {
            Record record = mRecords.get(position);
            holder.bindRecord(record);
        }

        @Override
        public int getItemCount() {
            return mRecords.size();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }
}
