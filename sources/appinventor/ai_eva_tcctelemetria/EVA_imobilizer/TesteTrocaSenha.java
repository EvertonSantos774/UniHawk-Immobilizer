package appinventor.ai_eva_tcctelemetria.EVA_imobilizer;

import android.os.Bundle;
import androidx.fragment.app.FragmentTransaction;
import com.google.appinventor.components.common.PropertyTypeConstants;
import com.google.appinventor.components.runtime.AppInventorCompatActivity;
import com.google.appinventor.components.runtime.Button;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.FirebaseDB;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.HandlesEventDispatching;
import com.google.appinventor.components.runtime.Label;
import com.google.appinventor.components.runtime.Notifier;
import com.google.appinventor.components.runtime.PasswordTextBox;
import com.google.appinventor.components.runtime.TextBox;
import com.google.appinventor.components.runtime.Texting;
import com.google.appinventor.components.runtime.VerticalScrollArrangement;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.errors.StopBlocksExecution;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.ErrorMessages;
import com.google.appinventor.components.runtime.util.RetValManager;
import com.google.appinventor.components.runtime.util.RuntimeErrorAlert;
import com.google.youngandroid.C0718runtime;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.Promise;
import kawa.lib.C0731lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;

/* compiled from: TesteTrocaSenha.yail */
public class TesteTrocaSenha extends Form implements Runnable {
    static final SimpleSymbol Lit0 = ((SimpleSymbol) new SimpleSymbol("TesteTrocaSenha").readResolve());
    static final SimpleSymbol Lit1 = ((SimpleSymbol) new SimpleSymbol("getMessage").readResolve());
    static final SimpleSymbol Lit10 = ((SimpleSymbol) new SimpleSymbol("AppName").readResolve());
    static final FString Lit100 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    static final SimpleSymbol Lit101 = ((SimpleSymbol) new SimpleSymbol("PasswordTextBox2").readResolve());
    static final FString Lit102 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    static final FString Lit103 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit104 = ((SimpleSymbol) new SimpleSymbol("AlternarSenha").readResolve());
    static final IntNum Lit105;
    static final FString Lit106 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit107 = ((SimpleSymbol) new SimpleSymbol("FirebaseDB2").readResolve());
    static final PairWithPosition Lit108 = PairWithPosition.make(Lit11, PairWithPosition.make(Lit11, PairWithPosition.make(Lit11, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860343), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860338), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860332);
    static final PairWithPosition Lit109 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860493), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860485);
    static final SimpleSymbol Lit11;
    static final PairWithPosition Lit110 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860591), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860583);
    static final PairWithPosition Lit111 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860689), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860681);
    static final PairWithPosition Lit112 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860787), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860779);
    static final PairWithPosition Lit113 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860885), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860877);
    static final PairWithPosition Lit114 = PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860931), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860927), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860923), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860919), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860914);
    static final PairWithPosition Lit115 = PairWithPosition.make(Lit11, PairWithPosition.make(Lit184, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860960), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 860954);
    static final PairWithPosition Lit116 = PairWithPosition.make(Lit11, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 861038);
    static final SimpleSymbol Lit117 = ((SimpleSymbol) new SimpleSymbol("AlternarSenha$Click").readResolve());
    static final FString Lit118 = new FString("com.google.appinventor.components.runtime.Texting");
    static final SimpleSymbol Lit119 = ((SimpleSymbol) new SimpleSymbol("Texting1").readResolve());
    static final SimpleSymbol Lit12 = ((SimpleSymbol) new SimpleSymbol("BackgroundColor").readResolve());
    static final SimpleSymbol Lit120 = ((SimpleSymbol) new SimpleSymbol("ReceivingEnabled").readResolve());
    static final FString Lit121 = new FString("com.google.appinventor.components.runtime.Texting");
    static final FString Lit122 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final SimpleSymbol Lit123 = ((SimpleSymbol) new SimpleSymbol("Notifier1").readResolve());
    static final FString Lit124 = new FString("com.google.appinventor.components.runtime.Notifier");
    static final FString Lit125 = new FString("com.google.appinventor.components.runtime.FirebaseDB");
    static final SimpleSymbol Lit126 = ((SimpleSymbol) new SimpleSymbol("DefaultURL").readResolve());
    static final SimpleSymbol Lit127 = ((SimpleSymbol) new SimpleSymbol("DeveloperBucket").readResolve());
    static final SimpleSymbol Lit128 = ((SimpleSymbol) new SimpleSymbol("FirebaseToken").readResolve());
    static final SimpleSymbol Lit129 = ((SimpleSymbol) new SimpleSymbol("FirebaseURL").readResolve());
    static final IntNum Lit13;
    static final SimpleSymbol Lit130 = ((SimpleSymbol) new SimpleSymbol("ProjectBucket").readResolve());
    static final FString Lit131 = new FString("com.google.appinventor.components.runtime.FirebaseDB");
    static final SimpleSymbol Lit132 = ((SimpleSymbol) new SimpleSymbol("PhoneNumber").readResolve());
    static final SimpleSymbol Lit133 = ((SimpleSymbol) new SimpleSymbol("$value").readResolve());
    static final IntNum Lit134 = IntNum.make(4);
    static final PairWithPosition Lit135 = PairWithPosition.make(Lit185, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 979091), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 979085);
    static final SimpleSymbol Lit136 = ((SimpleSymbol) new SimpleSymbol("Message").readResolve());
    static final PairWithPosition Lit137 = PairWithPosition.make(Lit11, PairWithPosition.make(Lit11, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 979273), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 979267);
    static final SimpleSymbol Lit138 = ((SimpleSymbol) new SimpleSymbol("SendMessageDirect").readResolve());
    static final SimpleSymbol Lit139 = ((SimpleSymbol) new SimpleSymbol("FirebaseDB1$GotValue").readResolve());
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit140 = ((SimpleSymbol) new SimpleSymbol("GotValue").readResolve());
    static final FString Lit141 = new FString("com.google.appinventor.components.runtime.FirebaseDB");
    static final FString Lit142 = new FString("com.google.appinventor.components.runtime.FirebaseDB");
    static final PairWithPosition Lit143 = PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1048696), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1048691);
    static final PairWithPosition Lit144 = PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1048857), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1048852);
    static final SimpleSymbol Lit145 = ((SimpleSymbol) new SimpleSymbol("StoreValue").readResolve());
    static final PairWithPosition Lit146 = PairWithPosition.make(Lit11, PairWithPosition.make(Lit11, PairWithPosition.make(Lit11, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049056), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049051), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049045);
    static final PairWithPosition Lit147 = PairWithPosition.make(Lit185, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049227), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049221);
    static final IntNum Lit148 = IntNum.make(2);
    static final PairWithPosition Lit149 = PairWithPosition.make(Lit185, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049348), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049342);
    static final SimpleSymbol Lit15 = ((SimpleSymbol) new SimpleSymbol("Scrollable").readResolve());
    static final PairWithPosition Lit150 = PairWithPosition.make(Lit185, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049469), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049463);
    static final PairWithPosition Lit151 = PairWithPosition.make(Lit185, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049590), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049584);
    static final IntNum Lit152 = IntNum.make(6);
    static final PairWithPosition Lit153 = PairWithPosition.make(Lit185, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049750), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049744);
    static final IntNum Lit154 = IntNum.make(7);
    static final PairWithPosition Lit155 = PairWithPosition.make(Lit185, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049871), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049865);
    static final IntNum Lit156 = IntNum.make(8);
    static final PairWithPosition Lit157 = PairWithPosition.make(Lit185, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049992), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1049986);
    static final IntNum Lit158 = IntNum.make(9);
    static final PairWithPosition Lit159 = PairWithPosition.make(Lit185, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050113), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050107);
    static final SimpleSymbol Lit16 = ((SimpleSymbol) new SimpleSymbol("ShowListsAsJson").readResolve());
    static final IntNum Lit160 = IntNum.make(10);
    static final PairWithPosition Lit161 = PairWithPosition.make(Lit185, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050235), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050229);
    static final IntNum Lit162 = IntNum.make(11);
    static final PairWithPosition Lit163;
    static final PairWithPosition Lit164 = PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050429), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050425), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050421), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050417), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050413), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050409), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050405), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050401), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050397), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050393), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050388);
    static final PairWithPosition Lit165 = PairWithPosition.make(Lit11, PairWithPosition.make(Lit184, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050458), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050452);
    static final SimpleSymbol Lit166 = ((SimpleSymbol) new SimpleSymbol("ShowAlert").readResolve());
    static final PairWithPosition Lit167 = PairWithPosition.make(Lit11, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050559);
    static final PairWithPosition Lit168;
    static final SimpleSymbol Lit169 = ((SimpleSymbol) new SimpleSymbol("FirebaseDB2$GotValue").readResolve());
    static final SimpleSymbol Lit17 = ((SimpleSymbol) new SimpleSymbol("Sizing").readResolve());
    static final SimpleSymbol Lit170 = ((SimpleSymbol) new SimpleSymbol("get-simple-name").readResolve());
    static final SimpleSymbol Lit171 = ((SimpleSymbol) new SimpleSymbol("android-log-form").readResolve());
    static final SimpleSymbol Lit172 = ((SimpleSymbol) new SimpleSymbol("add-to-form-environment").readResolve());
    static final SimpleSymbol Lit173 = ((SimpleSymbol) new SimpleSymbol("lookup-in-form-environment").readResolve());
    static final SimpleSymbol Lit174 = ((SimpleSymbol) new SimpleSymbol("is-bound-in-form-environment").readResolve());
    static final SimpleSymbol Lit175 = ((SimpleSymbol) new SimpleSymbol("add-to-global-var-environment").readResolve());
    static final SimpleSymbol Lit176 = ((SimpleSymbol) new SimpleSymbol("add-to-events").readResolve());
    static final SimpleSymbol Lit177 = ((SimpleSymbol) new SimpleSymbol("add-to-components").readResolve());
    static final SimpleSymbol Lit178 = ((SimpleSymbol) new SimpleSymbol("add-to-global-vars").readResolve());
    static final SimpleSymbol Lit179 = ((SimpleSymbol) new SimpleSymbol("add-to-form-do-after-creation").readResolve());
    static final SimpleSymbol Lit18 = ((SimpleSymbol) new SimpleSymbol("Theme").readResolve());
    static final SimpleSymbol Lit180 = ((SimpleSymbol) new SimpleSymbol("send-error").readResolve());
    static final SimpleSymbol Lit181 = ((SimpleSymbol) new SimpleSymbol("dispatchEvent").readResolve());
    static final SimpleSymbol Lit182 = ((SimpleSymbol) new SimpleSymbol("dispatchGenericEvent").readResolve());
    static final SimpleSymbol Lit183 = ((SimpleSymbol) new SimpleSymbol("lookup-handler").readResolve());
    static final SimpleSymbol Lit184 = ((SimpleSymbol) new SimpleSymbol("any").readResolve());
    static final SimpleSymbol Lit185 = ((SimpleSymbol) new SimpleSymbol("list").readResolve());
    static final SimpleSymbol Lit19 = ((SimpleSymbol) new SimpleSymbol("Title").readResolve());
    static final SimpleSymbol Lit2 = ((SimpleSymbol) new SimpleSymbol("*the-null-value*").readResolve());
    static final FString Lit20 = new FString("com.google.appinventor.components.runtime.VerticalScrollArrangement");
    static final SimpleSymbol Lit21 = ((SimpleSymbol) new SimpleSymbol("VerticalScrollArrangement1").readResolve());
    static final IntNum Lit22;
    static final SimpleSymbol Lit23 = ((SimpleSymbol) new SimpleSymbol("Width").readResolve());
    static final IntNum Lit24 = IntNum.make(-2);
    static final FString Lit25 = new FString("com.google.appinventor.components.runtime.VerticalScrollArrangement");
    static final FString Lit26 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit27 = ((SimpleSymbol) new SimpleSymbol("btn_retornarTela").readResolve());
    static final SimpleSymbol Lit28 = ((SimpleSymbol) new SimpleSymbol("Height").readResolve());
    static final IntNum Lit29 = IntNum.make(-1005);
    static final SimpleSymbol Lit3 = ((SimpleSymbol) new SimpleSymbol("g$Codigo").readResolve());
    static final SimpleSymbol Lit30 = ((SimpleSymbol) new SimpleSymbol(Component.LISTVIEW_KEY_IMAGE).readResolve());
    static final IntNum Lit31 = IntNum.make(-1010);
    static final FString Lit32 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit33 = PairWithPosition.make(Lit11, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 172110);
    static final SimpleSymbol Lit34 = ((SimpleSymbol) new SimpleSymbol("btn_retornarTela$Click").readResolve());
    static final SimpleSymbol Lit35 = ((SimpleSymbol) new SimpleSymbol("Click").readResolve());
    static final FString Lit36 = new FString("com.google.appinventor.components.runtime.VerticalScrollArrangement");
    static final SimpleSymbol Lit37 = ((SimpleSymbol) new SimpleSymbol("pt1").readResolve());
    static final SimpleSymbol Lit38 = ((SimpleSymbol) new SimpleSymbol("AlignHorizontal").readResolve());
    static final IntNum Lit39 = IntNum.make(3);
    static final IntNum Lit4 = IntNum.make((int) ErrorMessages.ERROR_WEB_REQUEST_HEADER_NOT_TWO_ELEMENTS);
    static final IntNum Lit40;
    static final FString Lit41 = new FString("com.google.appinventor.components.runtime.VerticalScrollArrangement");
    static final FString Lit42 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit43 = ((SimpleSymbol) new SimpleSymbol("Label1").readResolve());
    static final SimpleSymbol Lit44 = ((SimpleSymbol) new SimpleSymbol("FontSize").readResolve());
    static final IntNum Lit45 = IntNum.make(20);
    static final SimpleSymbol Lit46 = ((SimpleSymbol) new SimpleSymbol("Text").readResolve());
    static final SimpleSymbol Lit47 = ((SimpleSymbol) new SimpleSymbol("TextAlignment").readResolve());
    static final IntNum Lit48 = IntNum.make(1);
    static final SimpleSymbol Lit49 = ((SimpleSymbol) new SimpleSymbol("TextColor").readResolve());
    static final IntNum Lit5 = IntNum.make(9999);
    static final IntNum Lit50;
    static final FString Lit51 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit52 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final SimpleSymbol Lit53 = ((SimpleSymbol) new SimpleSymbol("cx_email").readResolve());
    static final IntNum Lit54 = IntNum.make(15);
    static final SimpleSymbol Lit55 = ((SimpleSymbol) new SimpleSymbol("Hint").readResolve());
    static final IntNum Lit56;
    static final IntNum Lit57 = IntNum.make(250);
    static final FString Lit58 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final FString Lit59 = new FString("com.google.appinventor.components.runtime.Button");
    static final PairWithPosition Lit6 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 32859), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 32851);
    static final SimpleSymbol Lit60 = ((SimpleSymbol) new SimpleSymbol("EnviarCodigo").readResolve());
    static final IntNum Lit61;
    static final IntNum Lit62;
    static final FString Lit63 = new FString("com.google.appinventor.components.runtime.Button");
    static final SimpleSymbol Lit64 = ((SimpleSymbol) new SimpleSymbol("FirebaseDB1").readResolve());
    static final SimpleSymbol Lit65 = ((SimpleSymbol) new SimpleSymbol("GetValue").readResolve());
    static final PairWithPosition Lit66 = PairWithPosition.make(Lit11, PairWithPosition.make(Lit11, PairWithPosition.make(Lit11, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 401591), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 401586), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 401580);
    static final IntNum Lit67 = IntNum.make(100);
    static final PairWithPosition Lit68 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 401741), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 401733);
    static final PairWithPosition Lit69 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 401839), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 401831);
    static final PairWithPosition Lit7 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 32859), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 32851);
    static final PairWithPosition Lit70 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 401937), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 401929);
    static final PairWithPosition Lit71 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 402035), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 402027);
    static final PairWithPosition Lit72 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit14, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 402133), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 402125);
    static final PairWithPosition Lit73 = PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, PairWithPosition.make(Lit184, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 402179), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 402175), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 402171), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 402167), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 402162);
    static final PairWithPosition Lit74 = PairWithPosition.make(Lit11, PairWithPosition.make(Lit184, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 402208), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 402202);
    static final SimpleSymbol Lit75 = ((SimpleSymbol) new SimpleSymbol("EnviarCodigo$Click").readResolve());
    static final FString Lit76 = new FString("com.google.appinventor.components.runtime.VerticalScrollArrangement");
    static final SimpleSymbol Lit77 = ((SimpleSymbol) new SimpleSymbol("pt2").readResolve());
    static final IntNum Lit78;
    static final SimpleSymbol Lit79 = ((SimpleSymbol) new SimpleSymbol("Visible").readResolve());
    static final SimpleSymbol Lit8 = ((SimpleSymbol) new SimpleSymbol("ActionBar").readResolve());
    static final FString Lit80 = new FString("com.google.appinventor.components.runtime.VerticalScrollArrangement");
    static final FString Lit81 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit82 = ((SimpleSymbol) new SimpleSymbol("Label5").readResolve());
    static final IntNum Lit83;
    static final FString Lit84 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit85 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final SimpleSymbol Lit86 = ((SimpleSymbol) new SimpleSymbol("cx_codigo").readResolve());
    static final SimpleSymbol Lit87 = ((SimpleSymbol) new SimpleSymbol("NumbersOnly").readResolve());
    static final FString Lit88 = new FString("com.google.appinventor.components.runtime.TextBox");
    static final FString Lit89 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit9 = ((SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_BOOLEAN).readResolve());
    static final SimpleSymbol Lit90 = ((SimpleSymbol) new SimpleSymbol("Label3").readResolve());
    static final IntNum Lit91;
    static final FString Lit92 = new FString("com.google.appinventor.components.runtime.Label");
    static final FString Lit93 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    static final SimpleSymbol Lit94 = ((SimpleSymbol) new SimpleSymbol("PasswordTextBox1").readResolve());
    static final FString Lit95 = new FString("com.google.appinventor.components.runtime.PasswordTextBox");
    static final FString Lit96 = new FString("com.google.appinventor.components.runtime.Label");
    static final SimpleSymbol Lit97 = ((SimpleSymbol) new SimpleSymbol("Label4").readResolve());
    static final IntNum Lit98;
    static final FString Lit99 = new FString("com.google.appinventor.components.runtime.Label");
    public static TesteTrocaSenha TesteTrocaSenha;
    static final ModuleMethod lambda$Fn1 = null;
    static final ModuleMethod lambda$Fn10 = null;
    static final ModuleMethod lambda$Fn11 = null;
    static final ModuleMethod lambda$Fn12 = null;
    static final ModuleMethod lambda$Fn13 = null;
    static final ModuleMethod lambda$Fn14 = null;
    static final ModuleMethod lambda$Fn15 = null;
    static final ModuleMethod lambda$Fn16 = null;
    static final ModuleMethod lambda$Fn17 = null;
    static final ModuleMethod lambda$Fn18 = null;
    static final ModuleMethod lambda$Fn19 = null;
    static final ModuleMethod lambda$Fn2 = null;
    static final ModuleMethod lambda$Fn20 = null;
    static final ModuleMethod lambda$Fn21 = null;
    static final ModuleMethod lambda$Fn22 = null;
    static final ModuleMethod lambda$Fn23 = null;
    static final ModuleMethod lambda$Fn24 = null;
    static final ModuleMethod lambda$Fn25 = null;
    static final ModuleMethod lambda$Fn26 = null;
    static final ModuleMethod lambda$Fn27 = null;
    static final ModuleMethod lambda$Fn28 = null;
    static final ModuleMethod lambda$Fn29 = null;
    static final ModuleMethod lambda$Fn3 = null;
    static final ModuleMethod lambda$Fn30 = null;
    static final ModuleMethod lambda$Fn31 = null;
    static final ModuleMethod lambda$Fn32 = null;
    static final ModuleMethod lambda$Fn33 = null;
    static final ModuleMethod lambda$Fn34 = null;
    static final ModuleMethod lambda$Fn35 = null;
    static final ModuleMethod lambda$Fn36 = null;
    static final ModuleMethod lambda$Fn37 = null;
    static final ModuleMethod lambda$Fn4 = null;
    static final ModuleMethod lambda$Fn5 = null;
    static final ModuleMethod lambda$Fn6 = null;
    static final ModuleMethod lambda$Fn7 = null;
    static final ModuleMethod lambda$Fn8 = null;
    static final ModuleMethod lambda$Fn9 = null;
    public Boolean $Stdebug$Mnform$St;
    public final ModuleMethod $define;
    public Button AlternarSenha;
    public final ModuleMethod AlternarSenha$Click;
    public Button EnviarCodigo;
    public final ModuleMethod EnviarCodigo$Click;
    public FirebaseDB FirebaseDB1;
    public final ModuleMethod FirebaseDB1$GotValue;
    public FirebaseDB FirebaseDB2;
    public final ModuleMethod FirebaseDB2$GotValue;
    public Label Label1;
    public Label Label3;
    public Label Label4;
    public Label Label5;
    public Notifier Notifier1;
    public PasswordTextBox PasswordTextBox1;
    public PasswordTextBox PasswordTextBox2;
    public Texting Texting1;
    public VerticalScrollArrangement VerticalScrollArrangement1;
    public final ModuleMethod add$Mnto$Mncomponents;
    public final ModuleMethod add$Mnto$Mnevents;
    public final ModuleMethod add$Mnto$Mnform$Mndo$Mnafter$Mncreation;
    public final ModuleMethod add$Mnto$Mnform$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvar$Mnenvironment;
    public final ModuleMethod add$Mnto$Mnglobal$Mnvars;
    public final ModuleMethod android$Mnlog$Mnform;
    public Button btn_retornarTela;
    public final ModuleMethod btn_retornarTela$Click;
    public LList components$Mnto$Mncreate;
    public TextBox cx_codigo;
    public TextBox cx_email;
    public final ModuleMethod dispatchEvent;
    public final ModuleMethod dispatchGenericEvent;
    public LList events$Mnto$Mnregister;
    public LList form$Mndo$Mnafter$Mncreation;
    public Environment form$Mnenvironment;
    public Symbol form$Mnname$Mnsymbol;
    public final ModuleMethod get$Mnsimple$Mnname;
    public Environment global$Mnvar$Mnenvironment;
    public LList global$Mnvars$Mnto$Mncreate;
    public final ModuleMethod is$Mnbound$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod lookup$Mnhandler;
    public final ModuleMethod lookup$Mnin$Mnform$Mnenvironment;
    public final ModuleMethod onCreate;
    public final ModuleMethod process$Mnexception;
    public VerticalScrollArrangement pt1;
    public VerticalScrollArrangement pt2;
    public final ModuleMethod send$Mnerror;

    static {
        SimpleSymbol simpleSymbol = (SimpleSymbol) new SimpleSymbol(PropertyTypeConstants.PROPERTY_TYPE_TEXT).readResolve();
        Lit11 = simpleSymbol;
        Lit168 = PairWithPosition.make(simpleSymbol, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050669);
        SimpleSymbol simpleSymbol2 = Lit185;
        SimpleSymbol simpleSymbol3 = (SimpleSymbol) new SimpleSymbol("number").readResolve();
        Lit14 = simpleSymbol3;
        Lit163 = PairWithPosition.make(simpleSymbol2, PairWithPosition.make(simpleSymbol3, LList.Empty, "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050357), "/tmp/1669114921621_0.3360262884642956-0/youngandroidproject/../src/appinventor/ai_eva_tcctelemetria/EVA_imobilizer/TesteTrocaSenha.yail", 1050351);
        int[] iArr = new int[2];
        iArr[0] = -12560655;
        Lit105 = IntNum.make(iArr);
        int[] iArr2 = new int[2];
        iArr2[0] = -14336;
        Lit98 = IntNum.make(iArr2);
        int[] iArr3 = new int[2];
        iArr3[0] = -14336;
        Lit91 = IntNum.make(iArr3);
        int[] iArr4 = new int[2];
        iArr4[0] = -14336;
        Lit83 = IntNum.make(iArr4);
        int[] iArr5 = new int[2];
        iArr5[0] = -12303292;
        Lit78 = IntNum.make(iArr5);
        int[] iArr6 = new int[2];
        iArr6[0] = -1;
        Lit62 = IntNum.make(iArr6);
        int[] iArr7 = new int[2];
        iArr7[0] = -12560655;
        Lit61 = IntNum.make(iArr7);
        int[] iArr8 = new int[2];
        iArr8[0] = -1;
        Lit56 = IntNum.make(iArr8);
        int[] iArr9 = new int[2];
        iArr9[0] = -14336;
        Lit50 = IntNum.make(iArr9);
        int[] iArr10 = new int[2];
        iArr10[0] = -12303292;
        Lit40 = IntNum.make(iArr10);
        int[] iArr11 = new int[2];
        iArr11[0] = -12303292;
        Lit22 = IntNum.make(iArr11);
        int[] iArr12 = new int[2];
        iArr12[0] = -12303292;
        Lit13 = IntNum.make(iArr12);
    }

    public TesteTrocaSenha() {
        ModuleInfo.register(this);
        frame frame2 = new frame();
        frame2.$main = this;
        this.get$Mnsimple$Mnname = new ModuleMethod(frame2, 1, Lit170, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.onCreate = new ModuleMethod(frame2, 2, "onCreate", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.android$Mnlog$Mnform = new ModuleMethod(frame2, 3, Lit171, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnform$Mnenvironment = new ModuleMethod(frame2, 4, Lit172, 8194);
        this.lookup$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 5, Lit173, 8193);
        this.is$Mnbound$Mnin$Mnform$Mnenvironment = new ModuleMethod(frame2, 7, Lit174, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.add$Mnto$Mnglobal$Mnvar$Mnenvironment = new ModuleMethod(frame2, 8, Lit175, 8194);
        this.add$Mnto$Mnevents = new ModuleMethod(frame2, 9, Lit176, 8194);
        this.add$Mnto$Mncomponents = new ModuleMethod(frame2, 10, Lit177, 16388);
        this.add$Mnto$Mnglobal$Mnvars = new ModuleMethod(frame2, 11, Lit178, 8194);
        this.add$Mnto$Mnform$Mndo$Mnafter$Mncreation = new ModuleMethod(frame2, 12, Lit179, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.send$Mnerror = new ModuleMethod(frame2, 13, Lit180, FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.process$Mnexception = new ModuleMethod(frame2, 14, "process-exception", FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        this.dispatchEvent = new ModuleMethod(frame2, 15, Lit181, 16388);
        this.dispatchGenericEvent = new ModuleMethod(frame2, 16, Lit182, 16388);
        this.lookup$Mnhandler = new ModuleMethod(frame2, 17, Lit183, 8194);
        ModuleMethod moduleMethod = new ModuleMethod(frame2, 18, (Object) null, 0);
        moduleMethod.setProperty("source-location", "/tmp/runtime6229131719407907284.scm:627");
        lambda$Fn1 = moduleMethod;
        this.$define = new ModuleMethod(frame2, 19, "$define", 0);
        lambda$Fn2 = new ModuleMethod(frame2, 20, (Object) null, 0);
        lambda$Fn3 = new ModuleMethod(frame2, 21, (Object) null, 0);
        lambda$Fn4 = new ModuleMethod(frame2, 22, (Object) null, 0);
        lambda$Fn5 = new ModuleMethod(frame2, 23, (Object) null, 0);
        lambda$Fn6 = new ModuleMethod(frame2, 24, (Object) null, 0);
        lambda$Fn7 = new ModuleMethod(frame2, 25, (Object) null, 0);
        this.btn_retornarTela$Click = new ModuleMethod(frame2, 26, Lit34, 0);
        lambda$Fn8 = new ModuleMethod(frame2, 27, (Object) null, 0);
        lambda$Fn9 = new ModuleMethod(frame2, 28, (Object) null, 0);
        lambda$Fn10 = new ModuleMethod(frame2, 29, (Object) null, 0);
        lambda$Fn11 = new ModuleMethod(frame2, 30, (Object) null, 0);
        lambda$Fn12 = new ModuleMethod(frame2, 31, (Object) null, 0);
        lambda$Fn13 = new ModuleMethod(frame2, 32, (Object) null, 0);
        lambda$Fn14 = new ModuleMethod(frame2, 33, (Object) null, 0);
        lambda$Fn15 = new ModuleMethod(frame2, 34, (Object) null, 0);
        this.EnviarCodigo$Click = new ModuleMethod(frame2, 35, Lit75, 0);
        lambda$Fn16 = new ModuleMethod(frame2, 36, (Object) null, 0);
        lambda$Fn17 = new ModuleMethod(frame2, 37, (Object) null, 0);
        lambda$Fn18 = new ModuleMethod(frame2, 38, (Object) null, 0);
        lambda$Fn19 = new ModuleMethod(frame2, 39, (Object) null, 0);
        lambda$Fn20 = new ModuleMethod(frame2, 40, (Object) null, 0);
        lambda$Fn21 = new ModuleMethod(frame2, 41, (Object) null, 0);
        lambda$Fn22 = new ModuleMethod(frame2, 42, (Object) null, 0);
        lambda$Fn23 = new ModuleMethod(frame2, 43, (Object) null, 0);
        lambda$Fn24 = new ModuleMethod(frame2, 44, (Object) null, 0);
        lambda$Fn25 = new ModuleMethod(frame2, 45, (Object) null, 0);
        lambda$Fn26 = new ModuleMethod(frame2, 46, (Object) null, 0);
        lambda$Fn27 = new ModuleMethod(frame2, 47, (Object) null, 0);
        lambda$Fn28 = new ModuleMethod(frame2, 48, (Object) null, 0);
        lambda$Fn29 = new ModuleMethod(frame2, 49, (Object) null, 0);
        lambda$Fn30 = new ModuleMethod(frame2, 50, (Object) null, 0);
        lambda$Fn31 = new ModuleMethod(frame2, 51, (Object) null, 0);
        this.AlternarSenha$Click = new ModuleMethod(frame2, 52, Lit117, 0);
        lambda$Fn32 = new ModuleMethod(frame2, 53, (Object) null, 0);
        lambda$Fn33 = new ModuleMethod(frame2, 54, (Object) null, 0);
        lambda$Fn34 = new ModuleMethod(frame2, 55, (Object) null, 0);
        lambda$Fn35 = new ModuleMethod(frame2, 56, (Object) null, 0);
        this.FirebaseDB1$GotValue = new ModuleMethod(frame2, 57, Lit139, 8194);
        lambda$Fn36 = new ModuleMethod(frame2, 58, (Object) null, 0);
        lambda$Fn37 = new ModuleMethod(frame2, 59, (Object) null, 0);
        this.FirebaseDB2$GotValue = new ModuleMethod(frame2, 60, Lit169, 8194);
    }

    public Object lookupInFormEnvironment(Symbol symbol) {
        return lookupInFormEnvironment(symbol, Boolean.FALSE);
    }

    public void run() {
        CallContext instance = CallContext.getInstance();
        Consumer consumer = instance.consumer;
        instance.consumer = VoidConsumer.instance;
        try {
            run(instance);
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        ModuleBody.runCleanup(instance, th, consumer);
    }

    public final void run(CallContext $ctx) {
        String obj;
        Consumer $result = $ctx.consumer;
        C0718runtime.$instance.run();
        this.$Stdebug$Mnform$St = Boolean.FALSE;
        this.form$Mnenvironment = Environment.make(misc.symbol$To$String(Lit0));
        FString stringAppend = strings.stringAppend(misc.symbol$To$String(Lit0), "-global-vars");
        if (stringAppend == null) {
            obj = null;
        } else {
            obj = stringAppend.toString();
        }
        this.global$Mnvar$Mnenvironment = Environment.make(obj);
        TesteTrocaSenha = null;
        this.form$Mnname$Mnsymbol = Lit0;
        this.events$Mnto$Mnregister = LList.Empty;
        this.components$Mnto$Mncreate = LList.Empty;
        this.global$Mnvars$Mnto$Mncreate = LList.Empty;
        this.form$Mndo$Mnafter$Mncreation = LList.Empty;
        C0718runtime.$instance.run();
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addGlobalVarToCurrentFormEnvironment(Lit3, C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit4, Lit5), Lit6, "random integer")), $result);
        } else {
            addToGlobalVars(Lit3, lambda$Fn2);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit8, Boolean.TRUE, Lit9);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "EVA_imobilizer", Lit11);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit12, Lit13, Lit14);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit15, Boolean.TRUE, Lit9);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit16, Boolean.TRUE, Lit9);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit17, "Responsive", Lit11);
            C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit18, "AppTheme", Lit11);
            Values.writeValues(C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit19, "Redefinir Senha", Lit11), $result);
        } else {
            addToFormDoAfterCreation(new Promise(lambda$Fn3));
        }
        this.VerticalScrollArrangement1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit20, Lit21, lambda$Fn4), $result);
        } else {
            addToComponents(Lit0, Lit25, Lit21, lambda$Fn5);
        }
        this.btn_retornarTela = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit21, Lit26, Lit27, lambda$Fn6), $result);
        } else {
            addToComponents(Lit21, Lit32, Lit27, lambda$Fn7);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit34, this.btn_retornarTela$Click);
        } else {
            addToFormEnvironment(Lit34, this.btn_retornarTela$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "btn_retornarTela", "Click");
        } else {
            addToEvents(Lit27, Lit35);
        }
        this.pt1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit21, Lit36, Lit37, lambda$Fn8), $result);
        } else {
            addToComponents(Lit21, Lit41, Lit37, lambda$Fn9);
        }
        this.Label1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit37, Lit42, Lit43, lambda$Fn10), $result);
        } else {
            addToComponents(Lit37, Lit51, Lit43, lambda$Fn11);
        }
        this.cx_email = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit37, Lit52, Lit53, lambda$Fn12), $result);
        } else {
            addToComponents(Lit37, Lit58, Lit53, lambda$Fn13);
        }
        this.EnviarCodigo = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit37, Lit59, Lit60, lambda$Fn14), $result);
        } else {
            addToComponents(Lit37, Lit63, Lit60, lambda$Fn15);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit75, this.EnviarCodigo$Click);
        } else {
            addToFormEnvironment(Lit75, this.EnviarCodigo$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "EnviarCodigo", "Click");
        } else {
            addToEvents(Lit60, Lit35);
        }
        this.pt2 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit21, Lit76, Lit77, lambda$Fn16), $result);
        } else {
            addToComponents(Lit21, Lit80, Lit77, lambda$Fn17);
        }
        this.Label5 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit77, Lit81, Lit82, lambda$Fn18), $result);
        } else {
            addToComponents(Lit77, Lit84, Lit82, lambda$Fn19);
        }
        this.cx_codigo = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit77, Lit85, Lit86, lambda$Fn20), $result);
        } else {
            addToComponents(Lit77, Lit88, Lit86, lambda$Fn21);
        }
        this.Label3 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit77, Lit89, Lit90, lambda$Fn22), $result);
        } else {
            addToComponents(Lit77, Lit92, Lit90, lambda$Fn23);
        }
        this.PasswordTextBox1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit77, Lit93, Lit94, lambda$Fn24), $result);
        } else {
            addToComponents(Lit77, Lit95, Lit94, lambda$Fn25);
        }
        this.Label4 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit77, Lit96, Lit97, lambda$Fn26), $result);
        } else {
            addToComponents(Lit77, Lit99, Lit97, lambda$Fn27);
        }
        this.PasswordTextBox2 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit77, Lit100, Lit101, lambda$Fn28), $result);
        } else {
            addToComponents(Lit77, Lit102, Lit101, lambda$Fn29);
        }
        this.AlternarSenha = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit77, Lit103, Lit104, lambda$Fn30), $result);
        } else {
            addToComponents(Lit77, Lit106, Lit104, lambda$Fn31);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit117, this.AlternarSenha$Click);
        } else {
            addToFormEnvironment(Lit117, this.AlternarSenha$Click);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "AlternarSenha", "Click");
        } else {
            addToEvents(Lit104, Lit35);
        }
        this.Texting1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit118, Lit119, lambda$Fn32), $result);
        } else {
            addToComponents(Lit0, Lit121, Lit119, lambda$Fn33);
        }
        this.Notifier1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit122, Lit123, Boolean.FALSE), $result);
        } else {
            addToComponents(Lit0, Lit124, Lit123, Boolean.FALSE);
        }
        this.FirebaseDB1 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit125, Lit64, lambda$Fn34), $result);
        } else {
            addToComponents(Lit0, Lit131, Lit64, lambda$Fn35);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit139, this.FirebaseDB1$GotValue);
        } else {
            addToFormEnvironment(Lit139, this.FirebaseDB1$GotValue);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "FirebaseDB1", "GotValue");
        } else {
            addToEvents(Lit64, Lit140);
        }
        this.FirebaseDB2 = null;
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            Values.writeValues(C0718runtime.addComponentWithinRepl(Lit0, Lit141, Lit107, lambda$Fn36), $result);
        } else {
            addToComponents(Lit0, Lit142, Lit107, lambda$Fn37);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            C0718runtime.addToCurrentFormEnvironment(Lit169, this.FirebaseDB2$GotValue);
        } else {
            addToFormEnvironment(Lit169, this.FirebaseDB2$GotValue);
        }
        if (C0718runtime.$Stthis$Mnis$Mnthe$Mnrepl$St != Boolean.FALSE) {
            EventDispatcher.registerEventForDelegation((HandlesEventDispatching) C0718runtime.$Stthis$Mnform$St, "FirebaseDB2", "GotValue");
        } else {
            addToEvents(Lit107, Lit140);
        }
        C0718runtime.initRuntime();
    }

    static Object lambda3() {
        return C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit4, Lit5), Lit7, "random integer");
    }

    static Object lambda4() {
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit8, Boolean.TRUE, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit10, "EVA_imobilizer", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit12, Lit13, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit15, Boolean.TRUE, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit16, Boolean.TRUE, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit17, "Responsive", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit18, "AppTheme", Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit0, Lit19, "Redefinir Senha", Lit11);
    }

    static Object lambda5() {
        C0718runtime.setAndCoerceProperty$Ex(Lit21, Lit12, Lit22, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit21, Lit23, Lit24, Lit14);
    }

    static Object lambda6() {
        C0718runtime.setAndCoerceProperty$Ex(Lit21, Lit12, Lit22, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit21, Lit23, Lit24, Lit14);
    }

    static Object lambda7() {
        C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit28, Lit29, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit30, "setaCorreta-removebg-preview.png", Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit23, Lit31, Lit14);
    }

    static Object lambda8() {
        C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit28, Lit29, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit30, "setaCorreta-removebg-preview.png", Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit27, Lit23, Lit31, Lit14);
    }

    public Object btn_retornarTela$Click() {
        C0718runtime.setThisForm();
        return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen, LList.list1("Screen1"), Lit33, "open another screen");
    }

    static Object lambda10() {
        C0718runtime.setAndCoerceProperty$Ex(Lit37, Lit38, Lit39, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit37, Lit12, Lit40, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit37, Lit23, Lit24, Lit14);
    }

    static Object lambda9() {
        C0718runtime.setAndCoerceProperty$Ex(Lit37, Lit38, Lit39, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit37, Lit12, Lit40, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit37, Lit23, Lit24, Lit14);
    }

    static Object lambda11() {
        C0718runtime.setAndCoerceProperty$Ex(Lit43, Lit44, Lit45, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit43, Lit46, "E-mail", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit43, Lit47, Lit48, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit43, Lit49, Lit50, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit43, Lit23, Lit24, Lit14);
    }

    static Object lambda12() {
        C0718runtime.setAndCoerceProperty$Ex(Lit43, Lit44, Lit45, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit43, Lit46, "E-mail", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit43, Lit47, Lit48, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit43, Lit49, Lit50, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit43, Lit23, Lit24, Lit14);
    }

    static Object lambda13() {
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit44, Lit54, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit55, "E-mail cadastrado", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit47, Lit48, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit49, Lit56, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit23, Lit57, Lit14);
    }

    static Object lambda14() {
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit44, Lit54, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit55, "E-mail cadastrado", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit47, Lit48, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit49, Lit56, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit53, Lit23, Lit57, Lit14);
    }

    static Object lambda15() {
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit12, Lit61, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit44, Lit54, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit46, "Enviar Código", Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit49, Lit62, Lit14);
    }

    static Object lambda16() {
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit12, Lit61, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit44, Lit54, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit46, "Enviar Código", Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit60, Lit49, Lit62, Lit14);
    }

    public Object EnviarCodigo$Click() {
        C0718runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit64;
        SimpleSymbol simpleSymbol2 = Lit65;
        Object callYailPrimitive = C0718runtime.callYailPrimitive(C0718runtime.string$Mnreplace$Mnall, LList.list3(C0718runtime.get$Mnproperty.apply2(Lit53, Lit46), ".", "_"), Lit66, "replace all");
        ModuleMethod moduleMethod = C0718runtime.make$Mnyail$Mnlist;
        Pair list1 = LList.list1(C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit48, Lit67), Lit68, "random integer"));
        LList.chain4(list1, C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit48, Lit67), Lit69, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit48, Lit67), Lit70, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit48, Lit67), Lit71, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit48, Lit67), Lit72, "random integer"));
        return C0718runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list2(callYailPrimitive, C0718runtime.callYailPrimitive(moduleMethod, list1, Lit73, "make a list")), Lit74);
    }

    static Object lambda17() {
        C0718runtime.setAndCoerceProperty$Ex(Lit77, Lit38, Lit39, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit77, Lit12, Lit78, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit77, Lit79, Boolean.FALSE, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit77, Lit23, Lit24, Lit14);
    }

    static Object lambda18() {
        C0718runtime.setAndCoerceProperty$Ex(Lit77, Lit38, Lit39, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit77, Lit12, Lit78, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit77, Lit79, Boolean.FALSE, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit77, Lit23, Lit24, Lit14);
    }

    static Object lambda19() {
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit44, Lit45, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit46, "Código", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit47, Lit48, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit49, Lit83, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit23, Lit24, Lit14);
    }

    static Object lambda20() {
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit44, Lit45, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit46, "Código", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit47, Lit48, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit49, Lit83, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit82, Lit23, Lit24, Lit14);
    }

    static Object lambda21() {
        C0718runtime.setAndCoerceProperty$Ex(Lit86, Lit44, Lit54, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit86, Lit55, "1234", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit86, Lit87, Boolean.TRUE, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit86, Lit47, Lit48, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit86, Lit23, Lit57, Lit14);
    }

    static Object lambda22() {
        C0718runtime.setAndCoerceProperty$Ex(Lit86, Lit44, Lit54, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit86, Lit55, "1234", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit86, Lit87, Boolean.TRUE, Lit9);
        C0718runtime.setAndCoerceProperty$Ex(Lit86, Lit47, Lit48, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit86, Lit23, Lit57, Lit14);
    }

    static Object lambda23() {
        C0718runtime.setAndCoerceProperty$Ex(Lit90, Lit44, Lit45, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit90, Lit46, "Nova senha", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit90, Lit47, Lit48, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit90, Lit49, Lit91, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit90, Lit23, Lit24, Lit14);
    }

    static Object lambda24() {
        C0718runtime.setAndCoerceProperty$Ex(Lit90, Lit44, Lit45, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit90, Lit46, "Nova senha", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit90, Lit47, Lit48, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit90, Lit49, Lit91, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit90, Lit23, Lit24, Lit14);
    }

    static Object lambda25() {
        C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit44, Lit54, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit55, "********", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit47, Lit48, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit23, Lit57, Lit14);
    }

    static Object lambda26() {
        C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit44, Lit54, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit55, "********", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit47, Lit48, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit94, Lit23, Lit57, Lit14);
    }

    static Object lambda27() {
        C0718runtime.setAndCoerceProperty$Ex(Lit97, Lit44, Lit45, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit97, Lit46, "Confirmar nova senha", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit97, Lit47, Lit48, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit97, Lit49, Lit98, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit97, Lit23, Lit24, Lit14);
    }

    static Object lambda28() {
        C0718runtime.setAndCoerceProperty$Ex(Lit97, Lit44, Lit45, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit97, Lit46, "Confirmar nova senha", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit97, Lit47, Lit48, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit97, Lit49, Lit98, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit97, Lit23, Lit24, Lit14);
    }

    static Object lambda29() {
        C0718runtime.setAndCoerceProperty$Ex(Lit101, Lit44, Lit54, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit101, Lit55, "********", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit101, Lit47, Lit48, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit101, Lit23, Lit57, Lit14);
    }

    static Object lambda30() {
        C0718runtime.setAndCoerceProperty$Ex(Lit101, Lit44, Lit54, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit101, Lit55, "********", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit101, Lit47, Lit48, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit101, Lit23, Lit57, Lit14);
    }

    static Object lambda31() {
        C0718runtime.setAndCoerceProperty$Ex(Lit104, Lit12, Lit105, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit104, Lit44, Lit54, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit104, Lit46, "Alterar senha", Lit11);
    }

    static Object lambda32() {
        C0718runtime.setAndCoerceProperty$Ex(Lit104, Lit12, Lit105, Lit14);
        C0718runtime.setAndCoerceProperty$Ex(Lit104, Lit44, Lit54, Lit14);
        return C0718runtime.setAndCoerceProperty$Ex(Lit104, Lit46, "Alterar senha", Lit11);
    }

    public Object AlternarSenha$Click() {
        C0718runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit107;
        SimpleSymbol simpleSymbol2 = Lit65;
        Object callYailPrimitive = C0718runtime.callYailPrimitive(C0718runtime.string$Mnreplace$Mnall, LList.list3(C0718runtime.get$Mnproperty.apply2(Lit53, Lit46), ".", "_"), Lit108, "replace all");
        ModuleMethod moduleMethod = C0718runtime.make$Mnyail$Mnlist;
        Pair list1 = LList.list1(C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit48, Lit67), Lit109, "random integer"));
        LList.chain4(list1, C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit48, Lit67), Lit110, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit48, Lit67), Lit111, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit48, Lit67), Lit112, "random integer"), C0718runtime.callYailPrimitive(C0718runtime.random$Mninteger, LList.list2(Lit48, Lit67), Lit113, "random integer"));
        C0718runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list2(callYailPrimitive, C0718runtime.callYailPrimitive(moduleMethod, list1, Lit114, "make a list")), Lit115);
        return C0718runtime.callYailPrimitive(C0718runtime.open$Mnanother$Mnscreen, LList.list1("Screen1"), Lit116, "open another screen");
    }

    static Object lambda33() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit119, Lit120, Lit48, Lit14);
    }

    static Object lambda34() {
        return C0718runtime.setAndCoerceProperty$Ex(Lit119, Lit120, Lit48, Lit14);
    }

    static Object lambda35() {
        C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit126, "https://dazzling-fire-7140.firebaseio.com/", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit127, "eva:tcctelemetria@gmail:com/", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit128, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkIjp7InVpZCI6IjdkM2M0ZWJkLTUwYTktNDk0NC04YWQ2LWYzNzI0NWU5MmNjYSIsInByb2plY3QiOiJFVkFfaW1vYmlsaXplciIsImRldmVsb3BlciI6ImV2YTp0Y2N0ZWxlbWV0cmlhQGdtYWlsOmNvbSJ9LCJ2IjowLCJleHAiOjE2NzQ5OTg4NDA3LCJpYXQiOjE2NjU3NTgwMDd9.sOfTdDCRXYmCz9QDCgSF9NWdUjM3Uxm2OJ_ZkOBG0rg", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit129, "https://unihawk-immobilizer-v1-default-rtdb.firebaseio.com/", Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit130, "EVA_imobilizer", Lit11);
    }

    static Object lambda36() {
        C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit126, "https://dazzling-fire-7140.firebaseio.com/", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit127, "eva:tcctelemetria@gmail:com/", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit128, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkIjp7InVpZCI6IjdkM2M0ZWJkLTUwYTktNDk0NC04YWQ2LWYzNzI0NWU5MmNjYSIsInByb2plY3QiOiJFVkFfaW1vYmlsaXplciIsImRldmVsb3BlciI6ImV2YTp0Y2N0ZWxlbWV0cmlhQGdtYWlsOmNvbSJ9LCJ2IjowLCJleHAiOjE2NzQ5OTg4NDA3LCJpYXQiOjE2NjU3NTgwMDd9.sOfTdDCRXYmCz9QDCgSF9NWdUjM3Uxm2OJ_ZkOBG0rg", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit129, "https://unihawk-immobilizer-v1-default-rtdb.firebaseio.com/", Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit64, Lit130, "EVA_imobilizer", Lit11);
    }

    public Object FirebaseDB1$GotValue(Object $tag, Object $value) {
        C0718runtime.sanitizeComponentData($tag);
        Object $value2 = C0718runtime.sanitizeComponentData($value);
        C0718runtime.setThisForm();
        SimpleSymbol simpleSymbol = Lit119;
        SimpleSymbol simpleSymbol2 = Lit132;
        ModuleMethod moduleMethod = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            $value2 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit133), " is not bound in the current context"), "Unbound Variable");
        }
        C0718runtime.setAndCoerceProperty$Ex(simpleSymbol, simpleSymbol2, C0718runtime.callYailPrimitive(moduleMethod, LList.list2($value2, Lit134), Lit135, "select list item"), Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit119, Lit136, C0718runtime.callYailPrimitive(strings.string$Mnappend, LList.list2("Seu código: ", C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C0718runtime.$Stthe$Mnnull$Mnvalue$St)), Lit137, "join"), Lit11);
        C0718runtime.callComponentMethod(Lit119, Lit138, LList.Empty, LList.Empty);
        C0718runtime.setAndCoerceProperty$Ex(Lit37, Lit79, Boolean.FALSE, Lit9);
        return C0718runtime.setAndCoerceProperty$Ex(Lit77, Lit79, Boolean.TRUE, Lit9);
    }

    static Object lambda37() {
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit126, "https://dazzling-fire-7140.firebaseio.com/", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit127, "eva:tcctelemetria@gmail:com/", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit128, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkIjp7InVpZCI6IjdkM2M0ZWJkLTUwYTktNDk0NC04YWQ2LWYzNzI0NWU5MmNjYSIsInByb2plY3QiOiJFVkFfaW1vYmlsaXplciIsImRldmVsb3BlciI6ImV2YTp0Y2N0ZWxlbWV0cmlhQGdtYWlsOmNvbSJ9LCJ2IjowLCJleHAiOjE2NzQ5OTg4NDA3LCJpYXQiOjE2NjU3NTgwMDd9.sOfTdDCRXYmCz9QDCgSF9NWdUjM3Uxm2OJ_ZkOBG0rg", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit129, "https://unihawk-immobilizer-v1-default-rtdb.firebaseio.com/", Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit130, "EVA_imobilizer", Lit11);
    }

    static Object lambda38() {
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit126, "https://dazzling-fire-7140.firebaseio.com/", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit127, "eva:tcctelemetria@gmail:com/", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit128, "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJkIjp7InVpZCI6IjdkM2M0ZWJkLTUwYTktNDk0NC04YWQ2LWYzNzI0NWU5MmNjYSIsInByb2plY3QiOiJFVkFfaW1vYmlsaXplciIsImRldmVsb3BlciI6ImV2YTp0Y2N0ZWxlbWV0cmlhQGdtYWlsOmNvbSJ9LCJ2IjowLCJleHAiOjE2NzQ5OTg4NDA3LCJpYXQiOjE2NjU3NTgwMDd9.sOfTdDCRXYmCz9QDCgSF9NWdUjM3Uxm2OJ_ZkOBG0rg", Lit11);
        C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit129, "https://unihawk-immobilizer-v1-default-rtdb.firebaseio.com/", Lit11);
        return C0718runtime.setAndCoerceProperty$Ex(Lit107, Lit130, "EVA_imobilizer", Lit11);
    }

    public Object FirebaseDB2$GotValue(Object $tag, Object $value) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        Object obj8;
        Object obj9;
        C0718runtime.sanitizeComponentData($tag);
        Object $value2 = C0718runtime.sanitizeComponentData($value);
        C0718runtime.setThisForm();
        if (C0718runtime.callYailPrimitive(C0718runtime.yail$Mnequal$Qu, LList.list2(C0718runtime.get$Mnproperty.apply2(Lit86, Lit46), C0718runtime.lookupGlobalVarInCurrentFormEnvironment(Lit3, C0718runtime.$Stthe$Mnnull$Mnvalue$St)), Lit143, "=") == Boolean.FALSE) {
            return C0718runtime.callComponentMethod(Lit123, Lit166, LList.list1("Código Invalido"), Lit168);
        }
        if (C0718runtime.callYailPrimitive(C0718runtime.yail$Mnequal$Qu, LList.list2(C0718runtime.get$Mnproperty.apply2(Lit94, Lit46), C0718runtime.get$Mnproperty.apply2(Lit101, Lit46)), Lit144, "=") == Boolean.FALSE) {
            return C0718runtime.callComponentMethod(Lit123, Lit166, LList.list1("Senha Incorreta"), Lit167);
        }
        SimpleSymbol simpleSymbol = Lit107;
        SimpleSymbol simpleSymbol2 = Lit145;
        Object callYailPrimitive = C0718runtime.callYailPrimitive(C0718runtime.string$Mnreplace$Mnall, LList.list3(C0718runtime.get$Mnproperty.apply2(Lit53, Lit46), ".", "_"), Lit146, "replace all");
        ModuleMethod moduleMethod = C0718runtime.make$Mnyail$Mnlist;
        ModuleMethod moduleMethod2 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            obj = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit133), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj = $value2;
        }
        Pair list1 = LList.list1(C0718runtime.callYailPrimitive(moduleMethod2, LList.list2(obj, Lit48), Lit147, "select list item"));
        ModuleMethod moduleMethod3 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            obj2 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit133), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj2 = $value2;
        }
        Object callYailPrimitive2 = C0718runtime.callYailPrimitive(moduleMethod3, LList.list2(obj2, Lit148), Lit149, "select list item");
        ModuleMethod moduleMethod4 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            obj3 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit133), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj3 = $value2;
        }
        Object callYailPrimitive3 = C0718runtime.callYailPrimitive(moduleMethod4, LList.list2(obj3, Lit39), Lit150, "select list item");
        ModuleMethod moduleMethod5 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            obj4 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit133), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj4 = $value2;
        }
        Pair chain4 = LList.chain4(list1, callYailPrimitive2, callYailPrimitive3, C0718runtime.callYailPrimitive(moduleMethod5, LList.list2(obj4, Lit134), Lit151, "select list item"), C0718runtime.get$Mnproperty.apply2(Lit94, Lit46));
        ModuleMethod moduleMethod6 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            obj5 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit133), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj5 = $value2;
        }
        Object callYailPrimitive4 = C0718runtime.callYailPrimitive(moduleMethod6, LList.list2(obj5, Lit152), Lit153, "select list item");
        ModuleMethod moduleMethod7 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            obj6 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit133), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj6 = $value2;
        }
        Object callYailPrimitive5 = C0718runtime.callYailPrimitive(moduleMethod7, LList.list2(obj6, Lit154), Lit155, "select list item");
        ModuleMethod moduleMethod8 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            obj7 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit133), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj7 = $value2;
        }
        Object callYailPrimitive6 = C0718runtime.callYailPrimitive(moduleMethod8, LList.list2(obj7, Lit156), Lit157, "select list item");
        ModuleMethod moduleMethod9 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            obj8 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit133), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj8 = $value2;
        }
        Pair chain42 = LList.chain4(chain4, callYailPrimitive4, callYailPrimitive5, callYailPrimitive6, C0718runtime.callYailPrimitive(moduleMethod9, LList.list2(obj8, Lit158), Lit159, "select list item"));
        ModuleMethod moduleMethod10 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            obj9 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit133), " is not bound in the current context"), "Unbound Variable");
        } else {
            obj9 = $value2;
        }
        Pair chain1 = LList.chain1(chain42, C0718runtime.callYailPrimitive(moduleMethod10, LList.list2(obj9, Lit160), Lit161, "select list item"));
        ModuleMethod moduleMethod11 = C0718runtime.yail$Mnlist$Mnget$Mnitem;
        if ($value2 instanceof Package) {
            $value2 = C0718runtime.signalRuntimeError(strings.stringAppend("The variable ", C0718runtime.getDisplayRepresentation(Lit133), " is not bound in the current context"), "Unbound Variable");
        }
        LList.chain1(chain1, C0718runtime.callYailPrimitive(moduleMethod11, LList.list2($value2, Lit162), Lit163, "select list item"));
        return C0718runtime.callComponentMethod(simpleSymbol, simpleSymbol2, LList.list2(callYailPrimitive, C0718runtime.callYailPrimitive(moduleMethod, list1, Lit164, "make a list")), Lit165);
    }

    /* compiled from: TesteTrocaSenha.yail */
    public class frame extends ModuleBody {
        TesteTrocaSenha $main;

        public int match1(ModuleMethod moduleMethod, Object obj, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 1:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 2:
                    if (!(obj instanceof TesteTrocaSenha)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 3:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 5:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 7:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 12:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 13:
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                case 14:
                    if (!(obj instanceof TesteTrocaSenha)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 1;
                    return 0;
                default:
                    return super.match1(moduleMethod, obj, callContext);
            }
        }

        public int match2(ModuleMethod moduleMethod, Object obj, Object obj2, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 4:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 5:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 8:
                    if (!(obj instanceof Symbol)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 9:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 11:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 17:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 57:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                case 60:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 2;
                    return 0;
                default:
                    return super.match2(moduleMethod, obj, obj2, callContext);
            }
        }

        public int match4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 10:
                    callContext.value1 = obj;
                    callContext.value2 = obj2;
                    callContext.value3 = obj3;
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 4;
                    return 0;
                case 15:
                    if (!(obj instanceof TesteTrocaSenha)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Component)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    if (!(obj3 instanceof String)) {
                        return -786429;
                    }
                    callContext.value3 = obj3;
                    if (!(obj4 instanceof String)) {
                        return -786428;
                    }
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 4;
                    return 0;
                case 16:
                    if (!(obj instanceof TesteTrocaSenha)) {
                        return -786431;
                    }
                    callContext.value1 = obj;
                    if (!(obj2 instanceof Component)) {
                        return -786430;
                    }
                    callContext.value2 = obj2;
                    if (!(obj3 instanceof String)) {
                        return -786429;
                    }
                    callContext.value3 = obj3;
                    callContext.value4 = obj4;
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 4;
                    return 0;
                default:
                    return super.match4(moduleMethod, obj, obj2, obj3, obj4, callContext);
            }
        }

        public Object apply1(ModuleMethod moduleMethod, Object obj) {
            switch (moduleMethod.selector) {
                case 1:
                    return this.$main.getSimpleName(obj);
                case 2:
                    try {
                        this.$main.onCreate((Bundle) obj);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "onCreate", 1, obj);
                    }
                case 3:
                    this.$main.androidLogForm(obj);
                    return Values.empty;
                case 5:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lookup-in-form-environment", 1, obj);
                    }
                case 7:
                    try {
                        return this.$main.isBoundInFormEnvironment((Symbol) obj) ? Boolean.TRUE : Boolean.FALSE;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "is-bound-in-form-environment", 1, obj);
                    }
                case 12:
                    this.$main.addToFormDoAfterCreation(obj);
                    return Values.empty;
                case 13:
                    this.$main.sendError(obj);
                    return Values.empty;
                case 14:
                    this.$main.processException(obj);
                    return Values.empty;
                default:
                    return super.apply1(moduleMethod, obj);
            }
        }

        public Object apply4(ModuleMethod moduleMethod, Object obj, Object obj2, Object obj3, Object obj4) {
            boolean z = true;
            switch (moduleMethod.selector) {
                case 10:
                    this.$main.addToComponents(obj, obj2, obj3, obj4);
                    return Values.empty;
                case 15:
                    try {
                        try {
                            try {
                                try {
                                    return this.$main.dispatchEvent((Component) obj, (String) obj2, (String) obj3, (Object[]) obj4) ? Boolean.TRUE : Boolean.FALSE;
                                } catch (ClassCastException e) {
                                    throw new WrongType(e, "dispatchEvent", 4, obj4);
                                }
                            } catch (ClassCastException e2) {
                                throw new WrongType(e2, "dispatchEvent", 3, obj3);
                            }
                        } catch (ClassCastException e3) {
                            throw new WrongType(e3, "dispatchEvent", 2, obj2);
                        }
                    } catch (ClassCastException e4) {
                        throw new WrongType(e4, "dispatchEvent", 1, obj);
                    }
                case 16:
                    TesteTrocaSenha testeTrocaSenha = this.$main;
                    try {
                        Component component = (Component) obj;
                        try {
                            String str = (String) obj2;
                            try {
                                if (obj3 == Boolean.FALSE) {
                                    z = false;
                                }
                                try {
                                    testeTrocaSenha.dispatchGenericEvent(component, str, z, (Object[]) obj4);
                                    return Values.empty;
                                } catch (ClassCastException e5) {
                                    throw new WrongType(e5, "dispatchGenericEvent", 4, obj4);
                                }
                            } catch (ClassCastException e6) {
                                throw new WrongType(e6, "dispatchGenericEvent", 3, obj3);
                            }
                        } catch (ClassCastException e7) {
                            throw new WrongType(e7, "dispatchGenericEvent", 2, obj2);
                        }
                    } catch (ClassCastException e8) {
                        throw new WrongType(e8, "dispatchGenericEvent", 1, obj);
                    }
                default:
                    return super.apply4(moduleMethod, obj, obj2, obj3, obj4);
            }
        }

        public Object apply2(ModuleMethod moduleMethod, Object obj, Object obj2) {
            switch (moduleMethod.selector) {
                case 4:
                    try {
                        this.$main.addToFormEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e) {
                        throw new WrongType(e, "add-to-form-environment", 1, obj);
                    }
                case 5:
                    try {
                        return this.$main.lookupInFormEnvironment((Symbol) obj, obj2);
                    } catch (ClassCastException e2) {
                        throw new WrongType(e2, "lookup-in-form-environment", 1, obj);
                    }
                case 8:
                    try {
                        this.$main.addToGlobalVarEnvironment((Symbol) obj, obj2);
                        return Values.empty;
                    } catch (ClassCastException e3) {
                        throw new WrongType(e3, "add-to-global-var-environment", 1, obj);
                    }
                case 9:
                    this.$main.addToEvents(obj, obj2);
                    return Values.empty;
                case 11:
                    this.$main.addToGlobalVars(obj, obj2);
                    return Values.empty;
                case 17:
                    return this.$main.lookupHandler(obj, obj2);
                case 57:
                    return this.$main.FirebaseDB1$GotValue(obj, obj2);
                case 60:
                    return this.$main.FirebaseDB2$GotValue(obj, obj2);
                default:
                    return super.apply2(moduleMethod, obj, obj2);
            }
        }

        public Object apply0(ModuleMethod moduleMethod) {
            switch (moduleMethod.selector) {
                case 18:
                    return TesteTrocaSenha.lambda2();
                case 19:
                    this.$main.$define();
                    return Values.empty;
                case 20:
                    return TesteTrocaSenha.lambda3();
                case 21:
                    return TesteTrocaSenha.lambda4();
                case 22:
                    return TesteTrocaSenha.lambda5();
                case 23:
                    return TesteTrocaSenha.lambda6();
                case 24:
                    return TesteTrocaSenha.lambda7();
                case 25:
                    return TesteTrocaSenha.lambda8();
                case 26:
                    return this.$main.btn_retornarTela$Click();
                case 27:
                    return TesteTrocaSenha.lambda9();
                case 28:
                    return TesteTrocaSenha.lambda10();
                case 29:
                    return TesteTrocaSenha.lambda11();
                case 30:
                    return TesteTrocaSenha.lambda12();
                case 31:
                    return TesteTrocaSenha.lambda13();
                case 32:
                    return TesteTrocaSenha.lambda14();
                case 33:
                    return TesteTrocaSenha.lambda15();
                case 34:
                    return TesteTrocaSenha.lambda16();
                case 35:
                    return this.$main.EnviarCodigo$Click();
                case 36:
                    return TesteTrocaSenha.lambda17();
                case 37:
                    return TesteTrocaSenha.lambda18();
                case 38:
                    return TesteTrocaSenha.lambda19();
                case 39:
                    return TesteTrocaSenha.lambda20();
                case 40:
                    return TesteTrocaSenha.lambda21();
                case 41:
                    return TesteTrocaSenha.lambda22();
                case 42:
                    return TesteTrocaSenha.lambda23();
                case 43:
                    return TesteTrocaSenha.lambda24();
                case 44:
                    return TesteTrocaSenha.lambda25();
                case 45:
                    return TesteTrocaSenha.lambda26();
                case 46:
                    return TesteTrocaSenha.lambda27();
                case 47:
                    return TesteTrocaSenha.lambda28();
                case 48:
                    return TesteTrocaSenha.lambda29();
                case 49:
                    return TesteTrocaSenha.lambda30();
                case 50:
                    return TesteTrocaSenha.lambda31();
                case 51:
                    return TesteTrocaSenha.lambda32();
                case 52:
                    return this.$main.AlternarSenha$Click();
                case 53:
                    return TesteTrocaSenha.lambda33();
                case 54:
                    return TesteTrocaSenha.lambda34();
                case 55:
                    return TesteTrocaSenha.lambda35();
                case 56:
                    return TesteTrocaSenha.lambda36();
                case 58:
                    return TesteTrocaSenha.lambda37();
                case 59:
                    return TesteTrocaSenha.lambda38();
                default:
                    return super.apply0(moduleMethod);
            }
        }

        public int match0(ModuleMethod moduleMethod, CallContext callContext) {
            switch (moduleMethod.selector) {
                case 18:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 19:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 20:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 21:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 22:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 23:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 24:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 25:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 26:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 27:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 28:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 29:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 30:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 31:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 32:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 33:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 34:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 35:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 36:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 37:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 38:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 39:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 40:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 41:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 42:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 43:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 44:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 45:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 46:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 47:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 48:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 49:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 50:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 51:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 52:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 53:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 54:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 55:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 56:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 58:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                case 59:
                    callContext.proc = moduleMethod;
                    callContext.f229pc = 0;
                    return 0;
                default:
                    return super.match0(moduleMethod, callContext);
            }
        }
    }

    public String getSimpleName(Object object) {
        return object.getClass().getSimpleName();
    }

    public void onCreate(Bundle icicle) {
        AppInventorCompatActivity.setClassicModeFromYail(false);
        super.onCreate(icicle);
    }

    public void androidLogForm(Object message) {
    }

    public void addToFormEnvironment(Symbol name, Object object) {
        androidLogForm(Format.formatToString(0, "Adding ~A to env ~A with value ~A", name, this.form$Mnenvironment, object));
        this.form$Mnenvironment.put(name, object);
    }

    public Object lookupInFormEnvironment(Symbol name, Object default$Mnvalue) {
        boolean x = ((this.form$Mnenvironment == null ? 1 : 0) + 1) & true;
        if (x) {
            if (!this.form$Mnenvironment.isBound(name)) {
                return default$Mnvalue;
            }
        } else if (!x) {
            return default$Mnvalue;
        }
        return this.form$Mnenvironment.get(name);
    }

    public boolean isBoundInFormEnvironment(Symbol name) {
        return this.form$Mnenvironment.isBound(name);
    }

    public void addToGlobalVarEnvironment(Symbol name, Object object) {
        androidLogForm(Format.formatToString(0, "Adding ~A to env ~A with value ~A", name, this.global$Mnvar$Mnenvironment, object));
        this.global$Mnvar$Mnenvironment.put(name, object);
    }

    public void addToEvents(Object component$Mnname, Object event$Mnname) {
        this.events$Mnto$Mnregister = C0731lists.cons(C0731lists.cons(component$Mnname, event$Mnname), this.events$Mnto$Mnregister);
    }

    public void addToComponents(Object container$Mnname, Object component$Mntype, Object component$Mnname, Object init$Mnthunk) {
        this.components$Mnto$Mncreate = C0731lists.cons(LList.list4(container$Mnname, component$Mntype, component$Mnname, init$Mnthunk), this.components$Mnto$Mncreate);
    }

    public void addToGlobalVars(Object var, Object val$Mnthunk) {
        this.global$Mnvars$Mnto$Mncreate = C0731lists.cons(LList.list2(var, val$Mnthunk), this.global$Mnvars$Mnto$Mncreate);
    }

    public void addToFormDoAfterCreation(Object thunk) {
        this.form$Mndo$Mnafter$Mncreation = C0731lists.cons(thunk, this.form$Mndo$Mnafter$Mncreation);
    }

    public void sendError(Object error) {
        RetValManager.sendError(error == null ? null : error.toString());
    }

    public void processException(Object ex) {
        Object apply1 = Scheme.applyToArgs.apply1(GetNamedPart.getNamedPart.apply2(ex, Lit1));
        RuntimeErrorAlert.alert(this, apply1 == null ? null : apply1.toString(), ex instanceof YailRuntimeError ? ((YailRuntimeError) ex).getErrorType() : "Runtime Error", "End Application");
    }

    public boolean dispatchEvent(Component componentObject, String registeredComponentName, String eventName, Object[] args) {
        boolean x;
        SimpleSymbol registeredObject = misc.string$To$Symbol(registeredComponentName);
        if (!isBoundInFormEnvironment(registeredObject)) {
            EventDispatcher.unregisterEventForDelegation(this, registeredComponentName, eventName);
            return false;
        } else if (lookupInFormEnvironment(registeredObject) != componentObject) {
            return false;
        } else {
            try {
                Scheme.apply.apply2(lookupHandler(registeredComponentName, eventName), LList.makeList(args, 0));
                return true;
            } catch (StopBlocksExecution e) {
                return false;
            } catch (PermissionException exception) {
                exception.printStackTrace();
                if (this == componentObject) {
                    x = true;
                } else {
                    x = false;
                }
                if (!x ? x : IsEqual.apply(eventName, "PermissionNeeded")) {
                    processException(exception);
                } else {
                    PermissionDenied(componentObject, eventName, exception.getPermissionNeeded());
                }
                return false;
            } catch (Throwable exception2) {
                androidLogForm(exception2.getMessage());
                exception2.printStackTrace();
                processException(exception2);
                return false;
            }
        }
    }

    public void dispatchGenericEvent(Component componentObject, String eventName, boolean notAlreadyHandled, Object[] args) {
        Boolean bool;
        boolean x = true;
        Object handler = lookupInFormEnvironment(misc.string$To$Symbol(strings.stringAppend("any$", getSimpleName(componentObject), "$", eventName)));
        if (handler != Boolean.FALSE) {
            try {
                Apply apply = Scheme.apply;
                if (notAlreadyHandled) {
                    bool = Boolean.TRUE;
                } else {
                    bool = Boolean.FALSE;
                }
                apply.apply2(handler, C0731lists.cons(componentObject, C0731lists.cons(bool, LList.makeList(args, 0))));
            } catch (StopBlocksExecution e) {
            } catch (PermissionException exception) {
                exception.printStackTrace();
                if (this != componentObject) {
                    x = false;
                }
                if (!x ? x : IsEqual.apply(eventName, "PermissionNeeded")) {
                    processException(exception);
                } else {
                    PermissionDenied(componentObject, eventName, exception.getPermissionNeeded());
                }
            } catch (Throwable exception2) {
                androidLogForm(exception2.getMessage());
                exception2.printStackTrace();
                processException(exception2);
            }
        }
    }

    public Object lookupHandler(Object componentName, Object eventName) {
        String str = null;
        String obj = componentName == null ? null : componentName.toString();
        if (eventName != null) {
            str = eventName.toString();
        }
        return lookupInFormEnvironment(misc.string$To$Symbol(EventDispatcher.makeFullEventName(obj, str)));
    }

    public void $define() {
        Object reverse;
        Object obj;
        Object reverse2;
        Object obj2;
        Object obj3;
        Object var;
        Object component$Mnname;
        Object obj4;
        Language.setDefaults(Scheme.getInstance());
        try {
            run();
        } catch (Exception exception) {
            androidLogForm(exception.getMessage());
            processException(exception);
        }
        TesteTrocaSenha = this;
        addToFormEnvironment(Lit0, this);
        Object obj5 = this.events$Mnto$Mnregister;
        while (obj5 != LList.Empty) {
            try {
                Pair arg0 = (Pair) obj5;
                Object event$Mninfo = arg0.getCar();
                Object apply1 = C0731lists.car.apply1(event$Mninfo);
                String obj6 = apply1 == null ? null : apply1.toString();
                Object apply12 = C0731lists.cdr.apply1(event$Mninfo);
                EventDispatcher.registerEventForDelegation(this, obj6, apply12 == null ? null : apply12.toString());
                obj5 = arg0.getCdr();
            } catch (ClassCastException e) {
                throw new WrongType(e, "arg0", -2, obj5);
            }
        }
        try {
            LList components = C0731lists.reverse(this.components$Mnto$Mncreate);
            addToGlobalVars(Lit2, lambda$Fn1);
            reverse = C0731lists.reverse(this.form$Mndo$Mnafter$Mncreation);
            while (reverse != LList.Empty) {
                Pair arg02 = (Pair) reverse;
                misc.force(arg02.getCar());
                reverse = arg02.getCdr();
            }
            obj = components;
            while (obj != LList.Empty) {
                Pair arg03 = (Pair) obj;
                Object component$Mninfo = arg03.getCar();
                component$Mnname = C0731lists.caddr.apply1(component$Mninfo);
                C0731lists.cadddr.apply1(component$Mninfo);
                Object component$Mnobject = Invoke.make.apply2(C0731lists.cadr.apply1(component$Mninfo), lookupInFormEnvironment((Symbol) C0731lists.car.apply1(component$Mninfo)));
                SlotSet.set$Mnfield$Ex.apply3(this, component$Mnname, component$Mnobject);
                addToFormEnvironment((Symbol) component$Mnname, component$Mnobject);
                obj = arg03.getCdr();
            }
            reverse2 = C0731lists.reverse(this.global$Mnvars$Mnto$Mncreate);
            while (reverse2 != LList.Empty) {
                Pair arg04 = (Pair) reverse2;
                Object var$Mnval = arg04.getCar();
                var = C0731lists.car.apply1(var$Mnval);
                addToGlobalVarEnvironment((Symbol) var, Scheme.applyToArgs.apply1(C0731lists.cadr.apply1(var$Mnval)));
                reverse2 = arg04.getCdr();
            }
            LList component$Mndescriptors = components;
            obj2 = component$Mndescriptors;
            while (obj2 != LList.Empty) {
                Pair arg05 = (Pair) obj2;
                Object component$Mninfo2 = arg05.getCar();
                C0731lists.caddr.apply1(component$Mninfo2);
                Object init$Mnthunk = C0731lists.cadddr.apply1(component$Mninfo2);
                if (init$Mnthunk != Boolean.FALSE) {
                    Scheme.applyToArgs.apply1(init$Mnthunk);
                }
                obj2 = arg05.getCdr();
            }
            obj3 = component$Mndescriptors;
            while (obj3 != LList.Empty) {
                Pair arg06 = (Pair) obj3;
                Object component$Mninfo3 = arg06.getCar();
                Object component$Mnname2 = C0731lists.caddr.apply1(component$Mninfo3);
                C0731lists.cadddr.apply1(component$Mninfo3);
                callInitialize(SlotGet.field.apply2(this, component$Mnname2));
                obj3 = arg06.getCdr();
            }
        } catch (ClassCastException e2) {
            throw new WrongType(e2, "arg0", -2, obj3);
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "arg0", -2, obj2);
        } catch (ClassCastException e4) {
            throw new WrongType(e4, "add-to-global-var-environment", 0, var);
        } catch (ClassCastException e5) {
            throw new WrongType(e5, "arg0", -2, reverse2);
        } catch (ClassCastException e6) {
            throw new WrongType(e6, "add-to-form-environment", 0, component$Mnname);
        } catch (ClassCastException e7) {
            throw new WrongType(e7, "lookup-in-form-environment", 0, obj4);
        } catch (ClassCastException e8) {
            throw new WrongType(e8, "arg0", -2, obj);
        } catch (ClassCastException e9) {
            throw new WrongType(e9, "arg0", -2, reverse);
        } catch (YailRuntimeError exception2) {
            processException(exception2);
        }
    }

    public static SimpleSymbol lambda1symbolAppend$V(Object[] argsArray) {
        LList symbols = LList.makeList(argsArray, 0);
        Apply apply = Scheme.apply;
        ModuleMethod moduleMethod = strings.string$Mnappend;
        Object obj = LList.Empty;
        LList lList = symbols;
        while (lList != LList.Empty) {
            try {
                Pair arg0 = (Pair) lList;
                Object arg02 = arg0.getCdr();
                Object car = arg0.getCar();
                try {
                    obj = Pair.make(misc.symbol$To$String((Symbol) car), obj);
                    lList = arg02;
                } catch (ClassCastException e) {
                    throw new WrongType(e, "symbol->string", 1, car);
                }
            } catch (ClassCastException e2) {
                throw new WrongType(e2, "arg0", -2, lList);
            }
        }
        Object apply2 = apply.apply2(moduleMethod, LList.reverseInPlace(obj));
        try {
            return misc.string$To$Symbol((CharSequence) apply2);
        } catch (ClassCastException e3) {
            throw new WrongType(e3, "string->symbol", 1, apply2);
        }
    }

    static Object lambda2() {
        return null;
    }
}
