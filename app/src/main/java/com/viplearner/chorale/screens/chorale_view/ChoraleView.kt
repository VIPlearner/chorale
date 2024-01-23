package com.viplearner.chorale.screens.chorale_view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material.icons.outlined.BreakfastDining
import androidx.compose.material.icons.outlined.FolderSpecial
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LockOpen
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.viplearner.chorale.R
import com.viplearner.chorale.components.ProgressDialog
import com.viplearner.chorale.model.SongDetails

@Composable
fun ChoraleView(
    factory: ChoraleViewModel.Factory,
    choraleId: String,
    onBackClick: () -> Unit
) {
    val viewModel: ChoraleViewModel = choraleViewModelProvider(
        factory = factory,
        choraleId = choraleId
    )

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    when(viewState){
        is ChoraleViewModel.ChoraleViewState.Loading -> {
            ProgressDialog()
        }
        is ChoraleViewModel.ChoraleViewState.Init -> {

        }
        is ChoraleViewModel.ChoraleViewState.Success -> {
            ChoraleContent(
                songDetails = (viewState as ChoraleViewModel.ChoraleViewState.Success).songDetails,
                onToggleAltoAlone = {  },
                onFolderClick = {  },
                onBreakfastClicked = {  },
                onHomeClick = onBackClick,
                onPadlockClick = {  }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChoraleContent(
    songDetails: SongDetails,
    onToggleAltoAlone: (Boolean) -> Unit,
    onFolderClick: () -> Unit,
    onHomeClick: () -> Unit,
    onPadlockClick: () -> Unit,
    onBreakfastClicked: () -> Unit
){
    var altoAlone: Boolean by remember {
        mutableStateOf(false)
    }
    Scaffold(
        containerColor = Color.Black,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Chorale",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        style =
                        TextStyle(
                            fontFamily = FontFamily(
                                Font(
                                    resId = R.font.garamond_italic,
                                    style = FontStyle.Italic,
                                )
                            )
                        ),

                        color = Color(0xFF8ea814),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.W400
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        bottomBar = {
            BottomBar(
                modifier = Modifier.fillMaxWidth(),
                onFolderClick = onFolderClick,
                onHomeClick = onHomeClick,
                onPadlockClick = onPadlockClick,
                onBreakfastClicked = onBreakfastClicked
            )
        }
    ){
        val gradient = Brush.linearGradient(
            colors = listOf(Color(0xFF94AA16),Color(0xFF1F4D12)),
            start = Offset.Zero,
            end = Offset.Infinite
        )
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(30.dp))
                    .background(gradient)
                ,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp)) {
                        Switch(
                            checked = false, // State variable defining switch state
                            onCheckedChange = {  }, // Update state on switch change
                            modifier = Modifier, // Apply padding around the switch
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "Alto Alone",
                                style =
                                MaterialTheme.typography.bodyLarge.copy(
                                    fontFamily = FontFamily(
                                        Font(
                                            resId = R.font.raleway_regular,
                                            style = FontStyle.Italic,
                                        )
                                    )
                                ),
                                color = Color.White
                            )
                            Switch(
                                checked = false, // State variable defining switch state
                                onCheckedChange = {  }, // Update state on switch change
                                modifier = Modifier, // Apply padding around the switch
                                colors = SwitchDefaults.colors()
                            )
                        }
                    }
                    Text(
                        text = "Hallelujah Chorus",
                        style =
                        MaterialTheme.typography.headlineMedium.copy(
                            fontFamily = FontFamily(
                                Font(
                                    resId = R.font.raleway_regular,
                                    style = FontStyle.Italic,
                                )
                            )
                        ),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    IconButton(modifier = Modifier.size(70.dp), onClick = { /* Handle play button press */ }) {
                        Icon(
                            imageVector = Icons.Filled.PlayCircleFilled,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(70.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                    Divider(color = Color.Gray)
                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 25.dp),
                        text = songDetails.song,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = FontFamily(
                                Font(
                                    resId = R.font.garamond_italic,
                                    style = FontStyle.Italic,
                                )
                            )
                        ),
                        color = Color.White
                    )

                    Divider(color = Color.Gray)

                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 25.dp),
                        text = songDetails.solfa,
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = FontFamily(
                                Font(
                                    resId = R.font.raleway_regular,
                                    style = FontStyle.Italic,
                                )
                            ),
                            lineHeight = 40.sp
                        ),
                        color = Color.White
                    )

                }
            }
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    onFolderClick: () -> Unit,
    onHomeClick: () -> Unit,
    onPadlockClick: () -> Unit,
    onBreakfastClicked: () -> Unit
){
    BottomAppBar(
        modifier = modifier,
        containerColor = Color.Black,
        actions = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                BottomBarItem(icon = Icons.Outlined.FolderSpecial, onClick = onFolderClick)
                BottomBarItem(icon = Icons.Outlined.Home, onClick = onHomeClick)
                BottomBarItem(icon = Icons.Outlined.LockOpen, onClick = onPadlockClick)
                BottomBarItem(icon = Icons.Outlined.BreakfastDining, onClick = onBreakfastClicked)
            }
        }
    )
}

@Composable
fun BottomBarItem(
    icon : ImageVector,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.clickable { onClick() },
        color = Color(0xFF242229),
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            imageVector = icon,
            contentDescription = null,
            tint = Color.White
        )
    }
}