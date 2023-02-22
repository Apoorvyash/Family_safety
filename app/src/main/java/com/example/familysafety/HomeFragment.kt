package com.example.familysafety

import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {
    private val listContacts:ArrayList<InviteModel> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val listMembers= listOf<MemberModel>(
            MemberModel("apoorv yash", "opposite aditya chai corner , C-32 B", "320M", "29%"),
            MemberModel("govind krishna", "42-B niche colony udhamnagar" , "70m", "69%"),
            MemberModel("yash sharma", "opposite aditya chai corner" , "100m", "39%"),
            MemberModel("dipanshi tripathi", "himalaya conclave, adilnagar" , "1km", "99%"),
            MemberModel("kriti tripathi", "paathar bazaar ,bahraich" , "72km", "99%"),
                    MemberModel("kriti tripathi", "paathar bazaar ,bahraich" , "72km", "99%")
        )
        val adapter=ReAdapter(listMembers)
        val recycler=requireView().findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager= LinearLayoutManager(requireContext())
        recycler.adapter=adapter
        Log.d("FetchContacts89","Start kr dia")
        val inviteAdapter=InviteAdapter(listContacts)
        Log.d("FetchContacts89", "end ho gya bhai")

        CoroutineScope(Dispatchers.IO).launch{
            Log.d("FetchContact89","this is coroutine start")
            listContacts.addAll(fetchContacts())
            withContext(Dispatchers.Main){
                inviteAdapter.notifyDataSetChanged()
            }
            Log.d("FetchContact89","this is coroutine end")

        }


//        val listContact= listOf<InviteModel>(
//            InviteModel("apoorv", 123456789),
//            InviteModel("aditya", 123456789),
//            InviteModel("ma", 123456789),
//            InviteModel("skand", 123456789),
//            InviteModel("apoorv", 123456789),
//            InviteModel("aditya", 123456789),
//            InviteModel("ma", 123456789),
//            InviteModel("skand", 123456789)
//        )

        val inviteRecycle=requireView().findViewById<RecyclerView>(R.id.recycler_invite)
        inviteRecycle.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        inviteRecycle.adapter=inviteAdapter
    }
    private fun fetchContacts(): Collection<InviteModel> {
    val listContacts:ArrayList<InviteModel> = ArrayList()
        val contres=requireActivity().contentResolver
        val cursor = contres.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null)
        if(cursor !=null && cursor.count>0){
            while(cursor!=null && cursor.moveToNext()){
                val id=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                val name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                val hasPhoneNumber=cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                if(hasPhoneNumber>0){
                    val pcur=contres.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID+" = ?",
                    arrayOf(id),
                        ""
                    )
                    if(pcur!=null && pcur.count>0){
                        while(pcur!=null && pcur.moveToNext()){
                            val phoneNum=pcur.getString(pcur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            listContacts.add(InviteModel(name, phoneNum))
                        }
                        pcur.close()
                    }
                }

            }

        if(cursor!=null){
            cursor.close()
        }
        }
            return listContacts
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
            }
    }
}